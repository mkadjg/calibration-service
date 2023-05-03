package com.calibration.service;

import com.calibration.dto.EmployeeDto;
import com.calibration.model.Employees;
import com.calibration.model.Roles;
import com.calibration.model.UserRoles;
import com.calibration.model.Users;
import com.calibration.repository.*;
import com.calibration.util.PasswordHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeesRepository employeesRepository;

    UsersRepository usersRepository;

    EducationRepository educationRepository;

    JobPositionRepository jobPositionRepository;

    RolesRepository rolesRepository;

    UserRolesRepository userRolesRepository;

    @Autowired
    EmployeeServiceImpl(EmployeesRepository employeesRepository,
                        UsersRepository usersRepository,
                        EducationRepository educationRepository,
                        JobPositionRepository jobPositionRepository,
                        RolesRepository rolesRepository,
                        UserRolesRepository userRolesRepository) {
        this.employeesRepository = employeesRepository;
        this.usersRepository = usersRepository;
        this.educationRepository = educationRepository;
        this.jobPositionRepository = jobPositionRepository;
        this.rolesRepository = rolesRepository;
        this.userRolesRepository = userRolesRepository;
    }
    @Override
    public Employees create(EmployeeDto dto) {
        Users users = usersRepository.save(Users.builder()
                .username(dto.getUsername())
                .password(PasswordHashUtil.generate(dto.getPassword()))
                .build());

        Roles roles = rolesRepository.findById(2).orElseThrow(EntityNotFoundException::new);

        userRolesRepository.save(UserRoles.builder()
                .user(users)
                .role(roles)
                .build());

        return employeesRepository.save(Employees.builder()
                .nip(dto.getNip())
                .employeeName(dto.getEmployeeName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .users(users)
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
