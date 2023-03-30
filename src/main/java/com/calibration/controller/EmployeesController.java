package com.calibration.controller;

import com.calibration.dto.AutocompleteDto;
import com.calibration.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeesController {

    EmployeesRepository employeesRepository;

    @Autowired
    EmployeesController(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @GetMapping("/technician/autocomplete")
    public Object findAllTechnician() {
        return employeesRepository.findAllTechnician()
                .stream()
                .map(employees -> AutocompleteDto.builder()
                        .id(employees.getId())
                        .label(employees.getNip()
                                .concat(" - ")
                                .concat(employees.getEmployeeName())
                                .concat(" - ")
                                .concat(employees.getPhoneNumber()))
                        .build())
                .collect(Collectors.toList());
    }

    @GetMapping("/typewriter/autocomplete")
    public Object findAllTypewriter() {
        return employeesRepository.findAllTypewriter()
                .stream()
                .map(employees -> AutocompleteDto.builder()
                        .id(employees.getId())
                        .label(employees.getNip()
                                .concat(" - ")
                                .concat(employees.getEmployeeName())
                                .concat(" - ")
                                .concat(employees.getPhoneNumber()))
                        .build())
                .collect(Collectors.toList());
    }

}
