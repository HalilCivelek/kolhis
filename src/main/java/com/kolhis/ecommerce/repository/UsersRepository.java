package com.kolhis.ecommerce.repository;

import com.kolhis.ecommerce.entity.Users;

public interface UsersRepository extends BaseJpaRepository<Users, Long> {

    Users findFirstByUserName(String userName);

    Users findFirstByEmailAddress(String eMailAddress);

    Users findFirstByPhoneNumber(String phoneNumber);
}
