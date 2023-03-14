package com.calibration.service;

import com.calibration.dto.CustomerRegisterDto;
import com.calibration.model.Customers;
import com.calibration.model.Roles;
import com.calibration.model.UserRoles;
import com.calibration.model.Users;
import com.calibration.repository.CustomersRepository;
import com.calibration.repository.RolesRepository;
import com.calibration.repository.UserRolesRepository;
import com.calibration.repository.UsersRepository;
import com.calibration.util.PasswordHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    UsersRepository usersRepository;
    CustomersRepository customersRepository;
    UserRolesRepository userRolesRepository;
    RolesRepository rolesRepository;

    @Autowired
    CustomerServiceImpl(UsersRepository usersRepository, CustomersRepository customersRepository,
                        UserRolesRepository userRolesRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.customersRepository = customersRepository;
        this.userRolesRepository = userRolesRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public Object register(CustomerRegisterDto dto) {
        Users user = Users.builder()
                .username(dto.getUsername())
                .password(PasswordHashUtil.generate(dto.getPassword()))
                .build();
        user = usersRepository.save(user);

        Roles role = rolesRepository.findById(1).orElse(null);

        UserRoles userRole = UserRoles.builder()
                .user(user)
                .role(role)
                .build();
        userRolesRepository.save(userRole);

        Customers customer = Customers.builder()
                .companyName(dto.getCompanyName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .picName(dto.getPicName())
                .address(dto.getAddress())
                .users(user)
                .build();
        return customersRepository.save(customer);
    }
}
