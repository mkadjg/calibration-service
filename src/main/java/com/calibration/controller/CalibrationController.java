package com.calibration.controller;

import com.calibration.dto.CalibrationConfirmationDto;
import com.calibration.dto.CalibrationForwardDto;
import com.calibration.dto.CalibrationSubmissionDto;
import com.calibration.model.Calibration;
import com.calibration.repository.CalibrationRepository;
import com.calibration.service.CalibrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/calibration")
public class CalibrationController {

    CalibrationRepository calibrationRepository;

    CalibrationService calibrationService;

    @Autowired
    public CalibrationController(CalibrationRepository calibrationRepository,
                                 CalibrationService calibrationService) {
        this.calibrationRepository = calibrationRepository;
        this.calibrationService = calibrationService;
    }

    @GetMapping("/find-by-customer-id/{customerId}")
    public Object findAll(@PathVariable int customerId) {
        return calibrationRepository.findByCustomerId(customerId);
    }

    @GetMapping("")
    public Object findAll() {
        return calibrationRepository.findAll();
    }

    @PostMapping("")
    @Transactional
    public Object create(@RequestBody CalibrationSubmissionDto dto) {
        return ResponseEntity.status(201).body(calibrationService.create(dto));
    }

    @PostMapping("/confirm/{calibrationId}")
    @Transactional
    public Object confirm(@RequestBody CalibrationConfirmationDto dto, @PathVariable int calibrationId) {
        Calibration calibration = calibrationRepository.findById(calibrationId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(calibrationService.confirm(dto, calibration));
    }

    @PostMapping("/forward-to-technician/{calibrationId}")
    @Transactional
    public Object forwardToTechnician(@RequestBody CalibrationForwardDto dto, @PathVariable int calibrationId) {
        Calibration calibration = calibrationRepository.findById(calibrationId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(calibrationService.forwardToTechnician(dto, calibration));
    }

    @PostMapping("/confirm-by-technician/{calibrationId}")
    public Object confirmByTechnician(@PathVariable int calibrationId) {
        Calibration calibration = calibrationRepository.findById(calibrationId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(calibrationService.confirmByTechnician(calibration));
    }

    @PostMapping("/forward-to-typewriter/{calibrationId}")
    @Transactional
    public Object forwardToTypewriter(@RequestBody CalibrationForwardDto dto, @PathVariable int calibrationId) {
        Calibration calibration = calibrationRepository.findById(calibrationId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(calibrationService.forwardToTypewriter(dto, calibration));
    }

    @PostMapping("/confirm-by-typewriter/{calibrationId}")
    public Object confirmByTypewriter(@PathVariable int calibrationId) {
        Calibration calibration = calibrationRepository.findById(calibrationId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(calibrationService.confirmByTypewriter(calibration));
    }

}
