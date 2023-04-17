package com.calibration.controller;

import com.calibration.dto.CertificateCalibrationDto;
import com.calibration.model.Calibration;
import com.calibration.repository.CalibrationRepository;
import com.calibration.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    CalibrationRepository calibrationRepository;

    CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService,
                                 CalibrationRepository calibrationRepository) {
        this.certificateService = certificateService;
        this.calibrationRepository = calibrationRepository;
    }

    @PostMapping("/{calibrationId}")
    public Object create(@PathVariable int calibrationId,
                         @RequestBody CertificateCalibrationDto dto) {
        Calibration calibration = calibrationRepository.findById(calibrationId).orElseThrow(EntityNotFoundException::new);
        return certificateService.create(calibration, dto);
    }

}
