package com.calibration.service;

import com.calibration.dto.CalibrationReportDto;
import com.calibration.model.Calibration;
import com.calibration.model.CalibrationReport;
import com.calibration.repository.CalibrationReportRepository;
import com.calibration.repository.CalibrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalibrationReportServiceImpl implements CalibrationReportService {

    CalibrationRepository calibrationRepository;

    CalibrationReportRepository calibrationReportRepository;

    @Autowired
    public CalibrationReportServiceImpl(CalibrationRepository calibrationRepository,
                           CalibrationReportRepository calibrationReportRepository) {
        this.calibrationRepository = calibrationRepository;
        this.calibrationReportRepository = calibrationReportRepository;


    }

    @Override
    public Calibration create(Calibration calibration, CalibrationReportDto dto) {
        Calibration result = calibrationRepository.save(calibration.toBuilder()
                .uncertainly(dto.getUncertainly())
                .confidenceLevel(dto.getConfidenceLevel())
                .coverageFactor(dto.getCoverageFactor())
                .envConditionTBefore(dto.getEnvConditionTBefore())
                .envConditionTAfter(dto.getEnvConditionTAfter())
                .envConditionRhBefore(dto.getEnvConditionRhBefore())
                .envConditionRhAfter(dto.getEnvConditionRhAfter())
                .build());

        if (!calibrationReportRepository.findByCalibrationId(calibration.getId()).isEmpty()) {
            calibrationReportRepository.deleteAllReportByCalibrationId(calibration.getId());
        }

        dto.getResult().forEach(report -> calibrationReportRepository.save(CalibrationReport.builder()
                .instrumentIndication(report.getInstrumentIndication())
                .standardIndicationUp(report.getStandardIndicationUp())
                .standardIndicationDown(report.getStandardIndicationDown())
                .correctionUp(report.getCorrectionUp())
                .correctionDown(report.getCorrectionDown())
                .calibration(result)
                .build()));
        return result;
    }
}
