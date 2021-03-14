package com.kolhis.ecommerce.repository;

import com.kolhis.ecommerce.entity.Users;

public interface UsersRepository extends BaseJpaRepository<Users, Long> {

    Users findFirstByUserName(String userName);

    Users findFirstByEmail(String eMail);

    Users findFirstByPhoneNumber(String phoneNumber);
}
