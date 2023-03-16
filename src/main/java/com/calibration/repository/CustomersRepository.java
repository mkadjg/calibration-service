package com.calibration.repository;

import com.calibration.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomersRepository extends JpaRepository<Customers, Integer> {

    @Query("select c from Customers c where c.users.id =:userId")
    Customers findByUserId(int userId);
}
