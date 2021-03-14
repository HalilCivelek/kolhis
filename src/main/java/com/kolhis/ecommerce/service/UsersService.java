package com.kolhis.ecommerce.service;

import com.kolhis.ecommerce.entity.Address;
import com.kolhis.ecommerce.entity.Users;
import com.kolhis.ecommerce.entity.dto.UsersDto;
import com.kolhis.ecommerce.repository.AddressRepository;
import com.kolhis.ecommerce.repository.BaseJpaRepository;
import com.kolhis.ecommerce.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sun.security.util.Password;

import javax.validation.ValidationException;
import java.util.List;

@Service
@Slf4j
public class UsersService extends AbstractEntityService<Users, Long> {


    @Autowired
    private UsersRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

   /* public UsersService(BCryptPasswordEncoder bCryptPasswordEncoder,
                         UsersRepository repository){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.repository = repository;

    }*/

    public Users saveUsers(Users users) {

        users.isValid(true);

        if (repository.findFirstByUserName(users.getUserName()) != null) {

            throw new ValidationException("Kullanıcı adı daha önce alınmış");

        }

        if (repository.findFirstByEmailAddress(users.getEmailAddress()) != null) {

            throw new ValidationException("Mail adresi daha önce kullanılmış");

        }

        if (repository.findFirstByPhoneNumber(users.getPhoneNumber()) != null) {

            throw new ValidationException("Telefon numarası daha önce kullanılmış");

        }


         users.setPassword(bCryptPasswordEncoder().encode(users.getPassword()));


        repository.save(users);

        if (users.getAddress() != null && !users.getAddress().isEmpty()) {

            List<Address> addressList = users.getAddress();

            addressList.stream().forEach(address -> {

                address.setUser(users);

                addressRepository.save(address);

            });


        }


        return users;


    }

    @Override
    public BaseJpaRepository<Users, Long> getJpaRepository() {
        return this.repository;
    }

    @Override
    public List<String> getSearchFields() {
        return null;
    }
}
