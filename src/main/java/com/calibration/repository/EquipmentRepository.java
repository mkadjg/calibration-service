package com.calibration.repository;

import com.calibration.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    @Query("select e from Equipment e where e.customers.id =:id")
    List<Equipment> findByCustomerId(int id);
}
