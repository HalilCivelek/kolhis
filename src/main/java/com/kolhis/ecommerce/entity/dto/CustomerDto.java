package com.kolhis.ecommerce.entity.dto;

import com.kolhis.ecommerce.repository.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class CustomerDto extends BaseDto {
    private String firstName;
    private String lastName;
}
