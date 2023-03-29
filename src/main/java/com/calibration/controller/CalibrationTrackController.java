package com.calibration.controller;

import com.calibration.repository.CalibrationTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calibration-track")
public class CalibrationTrackController {

    CalibrationTrackRepository calibrationTrackRepository;

    @Autowired
    CalibrationTrackController(CalibrationTrackRepository calibrationTrackRepository) {
        this.calibrationTrackRepository = calibrationTrackRepository;
    }

    @GetMapping("/find-by-calibration-id/{calibrationId}")
    public Object findByCalibrationId(@PathVariable int calibrationId) {
        return calibrationTrackRepository.findByCalibrationId(calibrationId);
    }

}
