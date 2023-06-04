package com.calibration.repository;

import com.calibration.model.CalibrationMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalibrationMethodRepository extends JpaRepository<CalibrationMethod, Integer> {
}
