package com.calibration.controller;

import com.calibration.dto.CustomerRegisterDto;
import com.calibration.repository.CustomersRepository;
import com.calibration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomersController {

    CustomerService customerService;

    CustomersRepository customersRepository;

    @Autowired
    CustomersController(CustomerService customerService, CustomersRepository customersRepository) {
        this.customerService = customerService;
        this.customersRepository = customersRepository;
    }

    @PostMapping("/register")
    public Object register(@RequestBody CustomerRegisterDto dto) {
        return customerService.register(dto);
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return customersRepository.findAll();
    }
}
