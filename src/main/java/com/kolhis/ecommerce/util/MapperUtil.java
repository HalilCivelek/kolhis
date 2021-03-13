package com.kolhis.ecommerce.util;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MapperUtil extends ModelMapper {

    public <D,T> List<D> mapAll(final Collection<T> entityList , Class<D> outClass){
        return entityList.stream().map(entity -> map(entity,outClass)).collect(Collectors.toList());
    }

}
