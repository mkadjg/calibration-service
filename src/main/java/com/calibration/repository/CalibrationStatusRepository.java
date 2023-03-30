package com.calibration.repository;

import com.calibration.model.CalibrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalibrationStatusRepository extends JpaRepository<CalibrationStatus, Integer> {
}
