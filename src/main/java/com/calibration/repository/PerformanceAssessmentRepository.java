package com.calibration.repository;

import com.calibration.model.PerformanceAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerformanceAssessmentRepository extends JpaRepository<PerformanceAssessment, Integer> {

    @Query("select pa from PerformanceAssessment pa where pa.calibration.id =:id")
    List<PerformanceAssessment> findByCalibrationId(int id);

    @Query("select avg(pa.rating) from PerformanceAssessment pa where pa.calibration.equipment.customers.id =:id")
    Float averageRateByCustomerId(int id);

    @Query("select avg(pa.rating) from PerformanceAssessment pa " +
            "where pa.calibration.equipment.customers.id =:id " +
            "and pa.calibration.technician.id =:technicianId")
    Float averageRateByCustomerIdAndTechnicianId(int id, int technicianId);

    @Query("select avg(pa.rating) from PerformanceAssessment pa " +
            "where pa.calibration.equipment.customers.id =:id " +
            "and pa.calibration.typewriter.id =:typewriterId")
    Float averageRateByCustomerIdAndTypewriterId(int id, int typewriterId);

}
