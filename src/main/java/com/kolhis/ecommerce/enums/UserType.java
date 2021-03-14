package com.kolhis.ecommerce.enums;

import java.io.Serializable;

public enum UserType implements Serializable {
    ADMIN("ADMIN", 1),
    CUSTOMER("CUSTOMER", 10);

    private String userType;
    private int UserTypeCode;

    UserType(String userType, int userTypeCode) {
        this.userType = userType;
        UserTypeCode = userTypeCode;
    }

    public String getUserType() {
        return userType;
    }

    public int getUserTypeCode() {
        return UserTypeCode;
    }


}
