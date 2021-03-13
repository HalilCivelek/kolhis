package com.kolhis.ecommerce.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column
    protected Long id;

  /*  @Column(
            name = "created_date",
            nullable = false
    )
    protected String createdDate;

    @Column(
            name = "last_modified_date"
    )
    protected String lastModifiedDate;*/


    public abstract <T extends BaseEntity> boolean isValid(boolean throwException);

    public abstract <T extends BaseEntity> void update(T entity);
}
