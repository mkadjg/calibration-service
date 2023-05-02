package com.calibration.service;

import com.calibration.dto.EmployeeDto;
import com.calibration.model.Employees;
import com.calibration.model.Users;
import com.calibration.repository.EducationRepository;
import com.calibration.repository.EmployeesRepository;
import com.calibration.repository.JobPositionRepository;
import com.calibration.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeesRepository employeesRepository;

    UsersRepository usersRepository;

    EducationRepository educationRepository;

    JobPositionRepository jobPositionRepository;

    @Autowired
    EmployeeServiceImpl(EmployeesRepository employeesRepository,
                        UsersRepository usersRepository,
                        EducationRepository educationRepository,
                        JobPositionRepository jobPositionRepository) {
        this.employeesRepository = employeesRepository;
        this.usersRepository = usersRepository;
        this.educationRepository = educationRepository;
        this.jobPositionRepository = jobPositionRepository;
    }
    @Override
    public Employees create(EmployeeDto dto) {
        return employeesRepository.save(Employees.builder()
                .nip(dto.getNip())
                .employeeName(dto.getEmployeeName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .users(usersRepository.save(Users.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .build()))
                .education(educationRepository.findById(dto.getEducationId())
                    .orElseThrow(EntityNotFoundException::new))
                .jobPosition(jobPositionRepository.findById(dto.getJobPositionId())
                    .orElseThrow(EntityNotFoundException::new))
                .build());
    }

    @Override
    public Employees update(Employees employees, EmployeeDto dto) {
        return employeesRepository.save(employees.toBuilder()
                .nip(dto.getNip())
                .employeeName(dto.getEmployeeName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .education(educationRepository.findById(dto.getEducationId())
                    .orElseThrow(EntityNotFoundException::new))
                .jobPosition(jobPositionRepository.findById(dto.getJobPositionId())
                    .orElseThrow(EntityNotFoundException::new))
                .build());
    }
}
