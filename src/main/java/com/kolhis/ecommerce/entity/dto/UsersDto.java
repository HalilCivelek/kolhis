package com.kolhis.ecommerce.entity.dto;

import com.kolhis.ecommerce.enums.UserType;
import com.kolhis.ecommerce.repository.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ToString(callSuper = true)
@ApiModel(value = "Users Data Transfer Object")
public class UsersDto extends BaseDto {

    @ApiModelProperty(value = "first name", required = true)
    private String firstName;
    @ApiModelProperty(value = "last name", required = true)
    private String lastName;
    @ApiModelProperty(value = "user name", required = true)
    private String userName;
    @ApiModelProperty(value = "phone number", required = true)
    private String phoneNumber;
    @ApiModelProperty(value = "email", required = true)
    private String email;
    @ApiModelProperty(value = "password", required = true)
    private String password;

    @ApiModelProperty(value = "user type", required = true)
    private UserType userType;

    private List<AddressDto> address ;
}
