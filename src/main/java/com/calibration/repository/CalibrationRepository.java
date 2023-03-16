package com.calibration.repository;

import com.calibration.model.Calibration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalibrationRepository extends JpaRepository<Calibration, Integer> {
}
