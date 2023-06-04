package com.calibration.controller;

import com.calibration.exception.UploadFileException;
import com.calibration.model.Calibration;
import com.calibration.repository.CalibrationRawDataRepository;
import com.calibration.repository.CalibrationRepository;
import com.calibration.service.CalibrationRawDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/calibration-raw-data")
public class CalibrationRawDataController {

    CalibrationRawDataService calibrationRawDataService;

    CalibrationRepository calibrationRepository;

    CalibrationRawDataRepository calibrationRawDataRepository;

    @Autowired
    public CalibrationRawDataController (CalibrationRawDataService calibrationRawDataService,
                                         CalibrationRepository calibrationRepository,
                                         CalibrationRawDataRepository calibrationRawDataRepository) {
        this.calibrationRawDataService = calibrationRawDataService;
        this.calibrationRepository = calibrationRepository;
        this.calibrationRawDataRepository = calibrationRawDataRepository;
    }

    @PostMapping("/{calibrationId}")
    public Object save(@PathVariable("calibrationId") int calibrationId, @RequestParam("files") MultipartFile[] files) {
        Calibration calibration = calibrationRepository.findById(calibrationId).orElseThrow(EntityNotFoundException::new);
        Arrays.stream(files).forEach(file -> {
            try {
                calibrationRawDataService.save(calibration, file);
            } catch (IOException e) {
                throw new UploadFileException(e.getLocalizedMessage());
            }
        });
        return ResponseEntity.ok().body("Successfully upload file");
    }

    @GetMapping("/{calibrationId}")
    public Object findAll(@PathVariable("calibrationId") Integer calibrationId) {
        return calibrationRawDataRepository.findByCalibrationId(calibrationId);
    }

}
