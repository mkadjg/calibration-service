package com.calibration.repository;

import com.calibration.model.StandardCalibrationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardCalibrationTypeRepository extends JpaRepository<StandardCalibrationType, Integer> {
}
