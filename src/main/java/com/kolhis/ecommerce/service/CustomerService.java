package com.kolhis.ecommerce.service;

import com.kolhis.ecommerce.entity.Customer;
import com.kolhis.ecommerce.repository.BaseJpaRepository;
import com.kolhis.ecommerce.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService extends AbstractEntityService<Customer, Long> {

    @Autowired
    private CustomerRepository repository;

    @Override
    public BaseJpaRepository<Customer, Long> getJpaRepository() {
        return this.repository;
    }

    @Override
    public List<String> getSearchFields() {
        return null;
    }
}
