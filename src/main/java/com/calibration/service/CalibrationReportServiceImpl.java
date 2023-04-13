package com.calibration.service;

import com.calibration.dto.CalibrationReportDto;
import com.calibration.model.Calibration;
import com.calibration.model.CalibrationReport;
import com.calibration.repository.CalibrationReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalibrationReportServiceImpl implements CalibrationReportService {

    CalibrationService calibrationService;

    CalibrationReportRepository calibrationReportRepository;

    @Autowired
    public CalibrationReportServiceImpl(CalibrationService calibrationService,
                           CalibrationReportRepository calibrationReportRepository) {
        this.calibrationService = calibrationService;
        this.calibrationReportRepository = calibrationReportRepository;


    }

    @Override
    public Calibration create(Calibration calibration, CalibrationReportDto dto) {
        Calibration result = calibrationService.doneByTechnician(calibration.toBuilder()
                .uncertainly(dto.getUncertainly())
                .confidenceLevel(dto.getConfidenceLevel())
                .coverageFactor(dto.getCoverageFactor())
                .build());

        dto.getResult().forEach(report -> calibrationReportRepository.save(CalibrationReport.builder()
                .instrumentIndication(report.getInstrumentIndication())
                .standardIndicationUp(report.getStandardInstrumentUp())
                .standardIndicationDown(report.getStandardInstrumentDown())
                .correctionUp(report.getCorrectionUp())
                .correctionDown(report.getCorrectionDown())
                .calibration(result)
                .build()));
        return result;
    }
}
