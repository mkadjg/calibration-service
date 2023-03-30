package com.calibration.repository;

import com.calibration.model.Customers;
import com.calibration.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

    @Query("select e from Employees e where e.users.id =:userId")
    Employees findByUserId(int userId);

    @Query("select e from Employees e where e.jobPosition.jobPositionName = 'Technician'")
    List<Employees> findAllTechnician();

    @Query("select e from Employees e where e.jobPosition.jobPositionName = 'Certificate'")
    List<Employees> findAllTypewriter();

}
