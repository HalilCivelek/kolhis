package com.kolhis.ecommerce.repository;

import lombok.Data;
import java.io.Serializable;

@Data
public class BaseDto implements Serializable {
    protected String id;
    protected String createdDate;
    protected String lastModifiedDate;
    protected Boolean status;
}
