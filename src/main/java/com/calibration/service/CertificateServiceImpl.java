package com.calibration.service;


import com.calibration.dto.CertificateCalibrationDto;
import com.calibration.model.Calibration;
import com.calibration.repository.CalibrationMethodRepository;
import com.calibration.repository.CalibrationRepository;
import com.calibration.repository.StandardCalibrationTypeRepository;
import com.calibration.repository.TraceableToSiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
public class CertificateServiceImpl implements CertificateService {

    CalibrationRepository calibrationRepository;

    CalibrationMethodRepository calibrationMethodRepository;

    StandardCalibrationTypeRepository standardCalibrationTypeRepository;

    TraceableToSiRepository traceableToSiRepository;

    @Autowired
    CertificateServiceImpl(CalibrationRepository calibrationRepository,
                           CalibrationMethodRepository calibrationMethodRepository,
                           StandardCalibrationTypeRepository standardCalibrationTypeRepository,
                           TraceableToSiRepository traceableToSiRepository) {
        this.calibrationRepository = calibrationRepository;
        this.calibrationMethodRepository = calibrationMethodRepository;
        this.standardCalibrationTypeRepository = standardCalibrationTypeRepository;
        this.traceableToSiRepository = traceableToSiRepository;
    }
    @Override
    public Calibration create(Calibration calibration, CertificateCalibrationDto dto) {
        return calibrationRepository.save(calibration.toBuilder()
                .certificateNumber(dto.getCertificateNumber())
                .calibrationMethod(dto.getCalibrationMethod())
                .calibrationMethode(calibrationMethodRepository.findById(dto.getCalibrationMethodId()).orElseThrow(EntityNotFoundException::new))
                .calibrationLocation(dto.getCalibrationLocation())
                .issuanceDate(LocalDate.parse(dto.getIssuanceDate()))
                .standardName(dto.getStandardName())
                .standardType(dto.getStandardType())
                .standardCalibrationType(standardCalibrationTypeRepository.findById(dto.getStandardTypeId()).orElseThrow(EntityNotFoundException::new))
                .standardSerialNumber(dto.getStandardSerialNumber())
                .standardTraceableToSI(dto.getStandardTraceableToSI())
                .traceableToSi(traceableToSiRepository.findById(dto.getStandardTraceableToSIId()).orElseThrow(EntityNotFoundException::new))
                .build());
    }
}
