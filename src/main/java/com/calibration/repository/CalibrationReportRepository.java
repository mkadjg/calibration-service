package com.calibration.repository;

import com.calibration.model.CalibrationReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalibrationReportRepository extends JpaRepository<CalibrationReport, Integer> {

    @Query("select cr from CalibrationReport  cr where cr.calibration.id =:id")
    List<CalibrationReport> findByCalibrationId(int id);

    @Modifying
    @Query("delete from CalibrationReport  cr where cr.calibration.id =:id")
    void deleteAllReportByCalibrationId(int id);

}
