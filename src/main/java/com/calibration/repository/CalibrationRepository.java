package com.calibration.repository;

import com.calibration.model.Calibration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalibrationRepository extends JpaRepository<Calibration, Integer> {

    @Query("select c from Calibration c where c.equipment.customers.id =:id")
    List<Calibration> findByCustomerId(int id);

    @Query("select c from Calibration c where c.technician.id =:id")
    List<Calibration> findByTechnicianId(int id);

    @Query("select c from Calibration c where c.typewriter.id =:id")
    List<Calibration> findByTypewriterId(int id);

}
