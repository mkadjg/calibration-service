package com.calibration.controller;

import com.calibration.repository.CalibrationMethodRepository;
import com.calibration.repository.StandardCalibrationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/standard-calibration-type")
public class StandardCalibrationTypeController {

    StandardCalibrationTypeRepository standardCalibrationTypeRepository;

    @Autowired
    public StandardCalibrationTypeController(StandardCalibrationTypeRepository standardCalibrationTypeRepository) {
        this.standardCalibrationTypeRepository = standardCalibrationTypeRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return ResponseEntity.ok().body(standardCalibrationTypeRepository.findAll());
    }

}
