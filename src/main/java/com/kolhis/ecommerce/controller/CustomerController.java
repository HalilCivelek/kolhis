package com.kolhis.ecommerce.controller;

import com.kolhis.ecommerce.controller.base.AbstractEntityController;
import com.kolhis.ecommerce.entity.Customer;
import com.kolhis.ecommerce.entity.dto.CustomerDto;
import com.kolhis.ecommerce.service.AbstractEntityService;
import com.kolhis.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CustomerController.END_POINT)
public class CustomerController extends AbstractEntityController<CustomerDto, Customer, Long> {

    static final String END_POINT = "/api/customer";

    @Autowired
    private CustomerService customerService;


    @PostMapping("/save")
    public CustomerDto saveCompany(@RequestBody CustomerDto dto) {
        return toDto(customerService.save(toEntity(dto)));
    }

    @Override
    protected AbstractEntityService<Customer, Long> getService() {
        return this.customerService;
    }

}
