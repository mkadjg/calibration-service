package com.calibration.repository;


import com.calibration.model.CalibrationTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalibrationTrackRepository extends JpaRepository<CalibrationTrack, Integer> {
    @Query("select ct from CalibrationTrack ct where ct.calibration.id =:id")
    List<CalibrationTrack> findByCalibrationId(Integer id);
}
