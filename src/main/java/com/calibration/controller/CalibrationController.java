package com.calibration.controller;

import com.calibration.repository.CalibrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calibration")
public class CalibrationController {

    CalibrationRepository calibrationRepository;

    @Autowired
    public CalibrationController(CalibrationRepository calibrationRepository) {
        this.calibrationRepository = calibrationRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return calibrationRepository.findAll();
    }

}
