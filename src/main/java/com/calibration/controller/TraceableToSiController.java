package com.calibration.controller;

import com.calibration.repository.StandardCalibrationTypeRepository;
import com.calibration.repository.TraceableToSiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traceable-to-si")
public class TraceableToSiController {

    TraceableToSiRepository traceableToSiRepository;

    @Autowired
    public TraceableToSiController(TraceableToSiRepository traceableToSiRepository) {
        this.traceableToSiRepository = traceableToSiRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return ResponseEntity.ok().body(traceableToSiRepository.findAll());
    }

}
