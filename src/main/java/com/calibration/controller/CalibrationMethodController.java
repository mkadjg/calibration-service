package com.calibration.controller;

import com.calibration.repository.CalibrationMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calibration-method")
public class CalibrationMethodController {

    CalibrationMethodRepository calibrationMethodRepository;

    @Autowired
    public CalibrationMethodController(CalibrationMethodRepository calibrationMethodRepository) {
        this.calibrationMethodRepository = calibrationMethodRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return ResponseEntity.ok().body(calibrationMethodRepository.findAll());
    }
}
