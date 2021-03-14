package com.kolhis.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    private String city;

    private String district;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    private Users user;

    @NotBlank
    private String address;
    private String addressDescription;

    @Override
    public <T extends BaseEntity> boolean isValid(boolean throwException) {
        return true;
    }

    @Override
    public <T extends BaseEntity> void update(T entity) {

    }
}
