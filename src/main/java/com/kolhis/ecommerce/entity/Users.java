package com.kolhis.ecommerce.entity;

import com.kolhis.ecommerce.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class Users extends BaseEntity{

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    private String phoneNumber;
    @NotBlank
    private String email;

    private UserType userType;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Address> address;


    @Override
    public <T extends BaseEntity> boolean isValid(boolean throwException) {
        if(getUserName() == null ) {
            if(throwException){
                throw new ValidationException("Username not null" );
            } else
                return false;
        }
        if(getPassword() == null ){
            if(throwException){
                throw new ValidationException("Şifre boş geçilemez" );
            } else
                return false;
        }



        return true;
    }

    @Override
    public <T extends BaseEntity> void update(T entity) {

    }
}
