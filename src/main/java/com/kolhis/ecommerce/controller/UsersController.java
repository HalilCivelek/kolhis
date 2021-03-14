package com.kolhis.ecommerce.controller;

import com.kolhis.ecommerce.controller.base.AbstractEntityController;
import com.kolhis.ecommerce.entity.Users;
import com.kolhis.ecommerce.entity.dto.UsersDto;
import com.kolhis.ecommerce.service.AbstractEntityService;
import com.kolhis.ecommerce.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UsersController.END_POINT)
@Api(value = "Users Controller", description = "Customer ile alakali adresleri besler")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UsersController extends AbstractEntityController<UsersDto, Users, Long> {


    static final String END_POINT = "/api/users";

    @Autowired
    private UsersService service;

    @PostMapping("/save")
    @ApiOperation(value = "save", notes = " Kullanıcı kaydeder")
    public UsersDto saveUsers(@RequestBody UsersDto dto) {
        return toDto(service.saveUsers(toEntity(dto)));
    }


    @Override
    protected AbstractEntityService<Users, Long> getService() {
        return null;
    }
}
