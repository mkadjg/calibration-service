package com.calibration.controller;

import com.calibration.dto.ComplainConfirmationDto;
import com.calibration.dto.ComplainForwardDto;
import com.calibration.dto.ComplainSubmissionDto;
import com.calibration.model.Calibration;
import com.calibration.model.Complain;
import com.calibration.repository.CalibrationRepository;
import com.calibration.repository.ComplainRepository;
import com.calibration.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.naming.ServiceUnavailableException;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/complain")
public class ComplainController {

    ComplainRepository complainRepository;

    CalibrationRepository calibrationRepository;

    ComplainService complainService;

    @Autowired
    public ComplainController(ComplainRepository complainRepository,
                              CalibrationRepository calibrationRepository,
                              ComplainService complainService) {
        this.complainRepository = complainRepository;
        this.calibrationRepository = calibrationRepository;
        this.complainService = complainService;
    }

    @GetMapping("/find-by-customer-id/{customerId}")
    public Object findByCustomerId(@PathVariable int customerId) {
        return complainRepository.findByCustomerId(customerId);
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return complainRepository.findAll();
    }

    @PostMapping("/{calibrationId}")
    @Transactional
    public Object create(@RequestBody ComplainSubmissionDto dto, @PathVariable int calibrationId) {
        Calibration calibration = calibrationRepository.findById(calibrationId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.status(201).body(complainService.create(calibration, dto));
    }

    @PostMapping("/confirm/{complainId}")
    @Transactional
    public Object confirm(@RequestBody ComplainConfirmationDto dto, @PathVariable int complainId) {
        Complain complain = complainRepository.findById(complainId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(complainService.confirm(dto, complain));
    }

    @PostMapping("/forward/{complainId}")
    @Transactional
    public Object forward(@RequestBody ComplainForwardDto dto, @PathVariable int complainId) throws ServiceUnavailableException {
        Complain complain = complainRepository.findById(complainId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(complainService.forward(dto, complain));
    }

    @PostMapping("/confirm-by-technician/{complainId}")
    public Object confirmByTechnician(@PathVariable int complainId) {
        Complain complain = complainRepository.findById(complainId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(complainService.confirmByTechnician(complain));
    }

    @PostMapping("/done-by-technician/{complainId}")
    public Object doneByTechnician(@PathVariable int complainId) {
        Complain complain = complainRepository.findById(complainId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(complainService.doneByTechnician(complain));
    }

    @PostMapping("/confirm-by-typewriter/{complainId}")
    public Object confirmByTypewriter(@PathVariable int complainId) {
        Complain complain = complainRepository.findById(complainId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(complainService.confirmByTypewriter(complain));
    }

    @PostMapping("/done-by-typewriter/{complainId}")
    public Object doneByTypewriter(@PathVariable int complainId) {
        Complain complain = complainRepository.findById(complainId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(complainService.doneByTypewriter(complain));
    }

    @PostMapping("/forward-to-customer/{complainId}")
    public Object forwardToCustomer(@PathVariable int complainId) {
        Complain complain = complainRepository.findById(complainId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(complainService.forwardToCustomer(complain));
    }

    @PostMapping("/finish-by-customer/{complainId}")
    @Transactional
    public Object finishByCustomer(@PathVariable int complainId) {
        Complain complain = complainRepository.findById(complainId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok().body(complainService.finishByCustomer(complain));
    }

}
