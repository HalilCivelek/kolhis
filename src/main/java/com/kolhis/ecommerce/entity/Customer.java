package com.kolhis.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    String firstName;
    String lastName;


    @Override
    public <T extends BaseEntity> boolean isValid(boolean throwException) {
        return false;
    }

    @Override
    public <T extends BaseEntity> void update(T entity) {

    }
}
