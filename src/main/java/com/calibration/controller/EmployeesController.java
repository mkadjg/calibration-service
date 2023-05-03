package com.calibration.controller;

import com.calibration.dto.AutocompleteDto;
import com.calibration.dto.EmployeeDto;
import com.calibration.model.Employees;
import com.calibration.repository.EmployeesRepository;
import com.calibration.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeesController {

    EmployeesRepository employeesRepository;

    EmployeeService employeeService;

    @Autowired
    EmployeesController(EmployeesRepository employeesRepository,
                        EmployeeService employeeService) {
        this.employeesRepository = employeesRepository;
        this.employeeService = employeeService;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return employeesRepository.findAll();
    }

    @PostMapping("/create")
    public Object create(@RequestBody EmployeeDto dto) {
        return ResponseEntity.status(201).body(employeeService.create(dto));
    }

    @PutMapping("/{employeeId}")
    public Object update(@PathVariable int employeeId, @RequestBody EmployeeDto dto) {
        Employees employees = employeesRepository.findById(employeeId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(employeeService.update(employees, dto));
    }

    @DeleteMapping("/{employeeId}")
    public Object delete(@PathVariable int employeeId) {
        employeesRepository.findById(employeeId)
                .ifPresentOrElse(employees -> employeesRepository.delete(employees),
                        () -> { throw new EntityNotFoundException(); });
        return ResponseEntity.status(200).body("Hapus data berhasil!");
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
