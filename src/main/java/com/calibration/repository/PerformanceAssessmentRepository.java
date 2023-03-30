package com.calibration.repository;

import com.calibration.model.PerformanceAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerformanceAssessmentRepository extends JpaRepository<PerformanceAssessment, Integer> {

    @Query("select pa from PerformanceAssessment pa where pa.calibration.id =:id")
    List<PerformanceAssessment> findByCalibrationId(int id);

}
