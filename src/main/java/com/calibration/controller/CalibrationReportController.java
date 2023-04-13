package com.calibration.controller;

import com.calibration.dto.CalibrationReportDto;
import com.calibration.model.Calibration;
import com.calibration.repository.CalibrationReportRepository;
import com.calibration.repository.CalibrationRepository;
import com.calibration.service.CalibrationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/calibration-report")
public class CalibrationReportController {

    CalibrationRepository calibrationRepository;

    CalibrationReportService calibrationReportService;

    CalibrationReportRepository calibrationReportRepository;

    @Autowired
    CalibrationReportController(CalibrationRepository calibrationRepository,
                                CalibrationReportService calibrationReportService,
                                CalibrationReportRepository calibrationReportRepository) {
        this.calibrationRepository = calibrationRepository;
        this.calibrationReportService = calibrationReportService;
        this.calibrationReportRepository = calibrationReportRepository;
    }

    @PostMapping("/{calibrationId}")
    @Transactional
    public Object create(@RequestBody CalibrationReportDto dto, @PathVariable int calibrationId) {
        Calibration calibration = calibrationRepository.findById(calibrationId).orElseThrow(EntityNotFoundException::new);
        return calibrationReportService.create(calibration, dto);
    }

    @GetMapping("/{calibrationId}")
    public Object findAll(@PathVariable int calibrationId) {
        return calibrationReportRepository.findByCalibrationId(calibrationId);
    }

}
