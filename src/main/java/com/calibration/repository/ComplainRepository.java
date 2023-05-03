package com.calibration.repository;

import com.calibration.model.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplainRepository extends JpaRepository<Complain, Integer> {

    @Query("select c from Complain c where c.calibration.equipment.customers.id=:id")
    List<Complain> findByCustomerId(int id);

    @Query("select c from Complain c where c.technician.id=:id")
    List<Complain> findByTechnicianId(int id);

    @Query("select c from Complain c where c.typewriter.id=:id")
    List<Complain> findByTypewriterId(int id);

}
