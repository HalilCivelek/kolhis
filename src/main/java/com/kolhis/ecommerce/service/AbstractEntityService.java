package com.kolhis.ecommerce.service;

import com.kolhis.ecommerce.entity.BaseEntity;
import com.kolhis.ecommerce.repository.BaseJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@CacheConfig(cacheNames = "cache")
public abstract class AbstractEntityService<T extends BaseEntity, PK extends Serializable> {
    BaseEntity entity = new BaseEntity() {
        @Override
        public <T extends BaseEntity> boolean isValid(boolean throwException) {
            return false;
        }

        @Override
        public <T extends BaseEntity> void update(T entity) {

        }
    };

    public abstract BaseJpaRepository<T, PK> getJpaRepository();

    public abstract List<String> getSearchFields();

    public List<Pair<String, String>> getLabelAndFieldNameForExcel() {
        return null;
    }

    protected T verifySave(T entity) {
        return entity;
    }

    protected T verifyPut(T theReal, T forSave) {
        return forSave;
    }

    protected T verifyDelete(T entity) {
        return entity;
    }

    @CacheEvict
    @Transactional
    public T save(T entity) {
        this.verifySave(entity);
        return this.getJpaRepository().save(entity);
    }

    @CacheEvict
    @Transactional
    public T put(PK id, T forSave) {
        T theReal = this.getEntity(id);
        this.verifyPut(theReal, forSave);
        theReal.update(forSave);
        return this.getJpaRepository().save(theReal);
    }

    @CacheEvict
    @Transactional
    public List<T> putAll(List<T> forSaveList) {
        List<T> updatedList = new ArrayList<>();
        forSaveList.forEach(forSave -> {
            T theReal = this.getEntity((PK) forSave.getId());
            this.verifyPut(theReal, forSave);
            theReal.update(forSave);
            updatedList.add(theReal);
        });
       return this.getJpaRepository().saveAll(updatedList);
    }

    @CacheEvict
    @Transactional
    public void delete(PK id) {
        T entity = this.getEntity(id);
        this.verifyDelete(entity);

        if (this.getJpaRepository() != null) {
            this.getJpaRepository().delete(entity);
        }
    }

  //  @Cacheable(keyGenerator = "customKeyGenerator")
    public T getEntity(PK id)  {
        Optional<T> entityOpt = this.getJpaRepository().findById(id);
        if (!entityOpt.isPresent()) {
            String className = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
           // throw new Exception(className + " bulunamadı !");
            return null;
        } else {
            return entityOpt.get();
        }
    }

    //@Cacheable(keyGenerator = "customKeyGenerator")
    public List<T> getEntities(List<PK> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }

         return this.getJpaRepository().findAllById(ids);

    }

  //  @Cacheable(keyGenerator = "customKeyGenerator")
    public List<T> all() {
        return this.getJpaRepository().findAll();

    }

    //  @Cacheable(keyGenerator = "customKeyGenerator")
    public PageImpl pageable(Pageable pageable) {

        Page resp;
        resp = this.getJpaRepository().findAll(pageable);
        long count = this.getJpaRepository().count();
        return new PageImpl<>(resp.getContent(), pageable, count);

    }

   // @Cacheable(keyGenerator = "customKeyGenerator")
   /*  public SearchResult search(String queryValue, Pageable pageable) {
        Specification<T> query = buildQuery(queryValue);

        Page<T> all = getJpaRepository().findAll(query, pageable);
        Long count = getJpaRepository().count(query);
        return new SearchResult(all.getContent(), count);
    }

    protected Specification<T> buildQuery(String queryValue) {
        List<SearchSpecification<T>> specs = new ArrayList<>();

        for (String field : getSearchFields()) {
            specs.add(new SearchSpecification(new SearchCriteria(field, ":", queryValue)));
        }

        Specification<T> query = null;
        for (int i = 0, specsSize = specs.size(); i < specsSize; i++) {
            SearchSpecification<T> spec = specs.get(i);
            if (i == 0) {
                query = Specification.where(spec);
            } else {
                query = query.or(spec);
            }
        }
        return query;
    }

   public ByteArrayInputStream getEditableRecordsAsExcel(MultiEditExportDto multiEditExportDto) {
        multiEditExportDto.isValid();

        if (getLabelAndFieldNameForExcel() == null && getLabelAndFieldNameForExcel().isEmpty()) {
            throw new ValidationException(MULTI_EDIT_NOT_ALLOWED);
        }

        List<T> records = null;
        if (multiEditExportDto.getRecordIds() != null && !multiEditExportDto.getRecordIds().isEmpty()) {
            records = this.getJpaRepository() != null ?
                    this.getJpaRepository().findAllById((Iterable<PK>) multiEditExportDto.getRecordIds()) :
                    new ArrayList<T>((Collection<? extends T>) this.getMongoRepository().findAllById((Iterable<PK>) multiEditExportDto.getRecordIds()));
        }

        if (multiEditExportDto.getIsAllRecords()) {
            if (multiEditExportDto.getQuery() != null && !multiEditExportDto.getQuery().isBlank()) {
                Specification<T> query = buildQuery(multiEditExportDto.getQuery());

                records = this.getJpaRepository().findAll(query);
            } else {
                records = this.getJpaRepository() != null ? this.getJpaRepository().findAll() : this.getMongoRepository().findAll();
            }
        }

        if (records.isEmpty()) {
            throw new ValidationException(MULTI_EDIT_EXPORT_DATA_NOT_FOUND);
        }

        GenerateExcelModel generateExcelModel = new GenerateExcelModel();
        generateExcelModel.setHeaderColor(IndexedColors.BLUE);
        generateExcelModel.setSheetName("multi-edit");
        generateExcelModel.setItems(records);
        generateExcelModel.setHeaderLabelFieldPairs(getLabelAndFieldNameForExcel());

        try {
            return ExcelUtil.toExcel(generateExcelModel);
        } catch (IOException e) {
            throw new GenerateExcelException(EXCEL_GENERATE_ERROR);
        }
    }

    public void importExcelForUpdate(XSSFSheet worksheet) {
        List<Pair<String, String>> labelAndFieldNameForExcel = getLabelAndFieldNameForExcel();
        if (labelAndFieldNameForExcel == null || labelAndFieldNameForExcel.isEmpty()) {
            throw new ValidationException(MULTI_EDIT_NOT_ALLOWED);
        }

        List<T> forSaveObjects = convertSheetToEntityList(worksheet);

        if (forSaveObjects == null || forSaveObjects.isEmpty()) {
            throw new ValidationException(MULTI_EDIT_NOT_ALLOWED);
        }

        for (T forSaveObject : forSaveObjects) {
            T realObject = getJpaRepository().getOne((PK)forSaveObject.getId());
            List<String> fieldNames = getLabelAndFieldNameForExcel().stream().map(Pair::getSecond).collect(Collectors.toList());

            for (Field declaredField : forSaveObject.getClass().getDeclaredFields()) {
                declaredField.setAccessible(true);
                if (!fieldNames.contains(declaredField.getName())) {
                    try {
                        declaredField.set(forSaveObject, declaredField.get(realObject));
                    } catch (IllegalAccessException exception) {
                        throw new ValidationException(MULTI_EDIT_IMPORT_ERROR);
                    }
                }
            }
        }

        putAll(forSaveObjects);
    }

    protected List<String> allEntityFieldNames() {
        Class entityClass = getEntityClass();
        return Arrays.asList(entityClass.getDeclaredFields()).stream().map(Field::getName).collect(Collectors.toList());
    }

    public Class getEntityClass() {
        for (Type actualTypeArgument : ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()) {
            Class entityClass = (Class) actualTypeArgument;
            if (entityClass.getSuperclass() == BaseEntity.class) {
                return entityClass;
            }
        }

        throw new NotFoundException("Entity not found.");
    }*/

}

