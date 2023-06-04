package com.calibration.repository;

import com.calibration.model.CalibrationRawData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalibrationRawDataRepository extends JpaRepository<CalibrationRawData, Integer> {

    @Query("select crd from CalibrationRawData crd where crd.calibration.id =:calibrationId")
    List<CalibrationRawData> findByCalibrationId(Integer calibrationId);
}
