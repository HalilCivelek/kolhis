package com.kolhis.ecommerce.entity.dto;

import com.kolhis.ecommerce.repository.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel(value = "Address Data Transfer Object")
public class AddressDto extends BaseDto {

    @ApiModelProperty(value = "city")
    private String city;

    @ApiModelProperty(value = "district")
    private String district;

    @ApiModelProperty(value = "address", required = true)
    private String address;

    @ApiModelProperty(value = "address description ")
    private String addressDescription;


}
