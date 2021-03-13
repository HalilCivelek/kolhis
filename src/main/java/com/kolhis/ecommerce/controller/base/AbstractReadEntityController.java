package com.kolhis.ecommerce.controller.base;

import com.kolhis.ecommerce.entity.BaseEntity;
import com.kolhis.ecommerce.repository.BaseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.Serializable;
import java.util.List;

@Component
public abstract class AbstractReadEntityController<DTO extends BaseDto, Entity extends BaseEntity, PK extends Serializable> extends AbstractBaseController<DTO, Entity, PK> {
    public AbstractReadEntityController() {
    }

   // @ExcludeLog
    //@LogDatabase
    @GetMapping({"/all"})
    public List<DTO> all() {
        List<Entity> all = this.getService().all();
        return this.toDto(all);
    }

  /*  @ExcludeLog
    @LogDatabase
    @GetMapping({"/pageable"})
    public PageImpl<DTO> pageable(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "size", defaultValue = "20") int size,
                                       @RequestParam(name = "sort", required = false) String sort,
                                       @RequestParam(name = "direction", required = false, defaultValue = "ASC") String direction,
                                       @RequestParam(name = "q", required = false) String queryValue) {
        Pageable pageable = getPageable(page, size, sort, direction);

        List<Entity> entities = new ArrayList<>();
        Long totalElements = 0L;
        if (queryValue != null && !queryValue.isBlank()) {
            SearchResult search = getService().search(queryValue, pageable);
            for (Object o : search.getContent()) {
                entities.add((Entity) o);
            }
            totalElements = search.getSize();
        } else {
            PageImpl<Entity> resultEntityPage = this.getService().pageable(pageable);
            entities = resultEntityPage.getContent();
            totalElements = resultEntityPage.getTotalElements();
        }

        List<DTO> resultResources = entities.stream().map(this::toResource).collect(Collectors.toList());
        return new PageImpl(resultResources, pageable, totalElements);
    }*/

   // @ExcludeLog
    //@LogDatabase
    @GetMapping({"/{id}"})
    public DTO get(@PathVariable("id") PK id) {
        return this.toDto(this.getService().getEntity(id));
    }

   // @ExcludeLog
  /*  @GetMapping({"/search"})
    public PageImpl<DTO> search(@RequestParam("q") String queryValue,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "20") int size,
                                     @RequestParam(name = "sort", required = false) String sort,
                                     @RequestParam(name = "direction", required = false, defaultValue = "ASC") String direction) {
        Pageable pageable = getPageable(page, size, sort, direction);
        SearchResult<Entity> search = this.getService().search(queryValue, pageable);

        if (search.getContent().isEmpty()) {
            return null;
        } else {
            return new PageImpl<>(toResource(search.getContent()), pageable, search.getSize());
        }
    }*/

   /* protected Pageable getPageable(int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page, size);
        if (StringUtils.isNotBlank(sort)) {
            pageable = PageRequest.of(page, size, Sort.Direction.valueOf(direction), sort);
        }

        return pageable;
    }*/
}

