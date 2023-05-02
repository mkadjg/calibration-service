package com.calibration.service;

import com.calibration.dto.EmployeeDto;
import com.calibration.model.Employees;

public interface EmployeeService {
    Employees create(EmployeeDto dto);
    Employees update(Employees employees, EmployeeDto dto);
}
