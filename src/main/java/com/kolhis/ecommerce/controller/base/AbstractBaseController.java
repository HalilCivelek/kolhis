package com.kolhis.ecommerce.controller.base;

import com.kolhis.ecommerce.entity.BaseEntity;
import com.kolhis.ecommerce.repository.BaseDto;
import com.kolhis.ecommerce.service.AbstractEntityService;
import com.kolhis.ecommerce.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractBaseController<DTO extends BaseDto, Entity extends BaseEntity, PK extends Serializable> {

    @Autowired
    private MapperUtil modelMapper;

    protected Class<Entity> entityClass;
    protected Class<DTO> dtoClass;

    public AbstractBaseController() {
        ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        this.dtoClass = (Class<DTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }


    protected abstract AbstractEntityService<Entity, PK> getService();


    protected Entity toEntity(DTO dto) {
        return dto != null ? (Entity) modelMapper.map(dto, entityClass) : null;
    }

    protected DTO toDto(Entity entity) {
        return entity != null ? modelMapper.map(entity, dtoClass) : null;
    }

    protected List<DTO> toDto(List<Entity> all) {
        return !all.isEmpty() ? (List<DTO>) all.stream().map(e ->
                (DTO) toDto(e)
        ).collect(Collectors.toList()) : null;
    }


    protected Entity toEntity(Optional<DTO> dto) {
        return dto.map(this::toEntity).orElse(null);
    }
}

