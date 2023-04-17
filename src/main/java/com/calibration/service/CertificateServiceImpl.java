package com.calibration.service;


import com.calibration.dto.CertificateCalibrationDto;
import com.calibration.model.Calibration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CertificateServiceImpl implements CertificateService {

    CalibrationService calibrationService;

    @Autowired
    CertificateServiceImpl(CalibrationService calibrationService) {
        this.calibrationService = calibrationService;
    }
    @Override
    public Calibration create(Calibration calibration, CertificateCalibrationDto dto) {
        return calibrationService.doneByTypewriter(calibration.toBuilder()
                .certificateNumber(dto.getCertificateNumber())
                .calibrationMethod(dto.getCalibrationMethod())
                .calibrationLocation(dto.getCalibrationLocation())
                .envConditionTBefore(dto.getEnvConditionTBefore())
                .envConditionTAfter(dto.getEnvConditionTAfter())
                .envConditionRhBefore(dto.getEnvConditionRhBefore())
                .envConditionRhAfter(dto.getEnvConditionRhAfter())
                .issuanceDate(LocalDate.parse(dto.getIssuanceDate()))
                .standardName(dto.getStandardName())
                .standardType(dto.getStandardType())
                .standardSerialNumber(dto.getStandardSerialNumber())
                .standardTraceableToSI(dto.getStandardTraceableToSI()).build());
    }
}
