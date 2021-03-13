package com.kolhis.ecommerce.entity.dto;

import com.kolhis.ecommerce.repository.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel(value = "Company Data Transfer Object")
public class CustomerDto extends BaseDto {
    @ApiModelProperty(value = "First Name", required = true)
    private String firstName;
    @ApiModelProperty(value = "Last Name", required = true)
    private String lastName;
}
