package com.calibration.service;

import com.calibration.dto.CalibrationConfirmationDto;
import com.calibration.dto.CalibrationForwardDto;
import com.calibration.dto.CalibrationSubmissionDto;
import com.calibration.model.Calibration;
import com.calibration.model.CalibrationStatus;
import com.calibration.model.CalibrationTrack;
import com.calibration.model.Employees;
import com.calibration.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class CalibrationServiceImpl implements CalibrationService {

    CalibrationRepository calibrationRepository;

    EquipmentRepository equipmentRepository;

    CalibrationStatusRepository calibrationStatusRepository;

    CalibrationTrackRepository calibrationTrackRepository;

    EmployeesRepository employeesRepository;

    @Autowired
    CalibrationServiceImpl(CalibrationRepository calibrationRepository,
                           EquipmentRepository equipmentRepository,
                           CalibrationStatusRepository calibrationStatusRepository,
                           CalibrationTrackRepository calibrationTrackRepository,
                           EmployeesRepository employeesRepository) {
        this.calibrationRepository = calibrationRepository;
        this.equipmentRepository = equipmentRepository;
        this.calibrationStatusRepository = calibrationStatusRepository;
        this.calibrationTrackRepository = calibrationTrackRepository;
        this.employeesRepository = employeesRepository;
    }

    @Override
    public Calibration create(CalibrationSubmissionDto dto) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(1)
                .orElseThrow(EntityNotFoundException::new);
        Date now = new Date();

        Calibration calibration = calibrationRepository.save(
                Calibration.builder()
                        .equipment(equipmentRepository.findById(dto.getEquipmentId())
                                .orElseThrow(EntityNotFoundException::new))
                        .calibrationNote(dto.getCalibrationNote())
                        .calibrationStatus(calibrationStatus)
                        .calibrationDate(now)
                .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(calibration)
                        .trackDate(now)
                        .build()
        );

        return calibration;
    }

    @Override
    public Calibration confirm(CalibrationConfirmationDto dto, Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(2)
                .orElseThrow(EntityNotFoundException::new);
        Date now = new Date();

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .orderNumber(dto.getOrderNumber())
                        .calibrationStatus(calibrationStatus)
                        .receivedDate(now)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(now)
                        .build()
        );

        return result;
    }

    @Override
    public Calibration forwardToTechnician(CalibrationForwardDto dto, Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(3)
                .orElseThrow(EntityNotFoundException::new);

        Employees technician = employeesRepository
                .findById(dto.getEmployeeId())
                .orElseThrow(EntityNotFoundException::new);

        Date now = new Date();

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .technician(technician)
                        .calibrationStatus(calibrationStatus)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(now)
                        .build()
        );

        return result;
    }

    @Override
    public Calibration confirmByTechnician(Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(4)
                .orElseThrow(EntityNotFoundException::new);
        Date now = new Date();

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .calibrationStatus(calibrationStatus)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(now)
                        .build()
        );

        return result;
    }

    @Override
    public Calibration forwardToTypewriter(CalibrationForwardDto dto, Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(5)
                .orElseThrow(EntityNotFoundException::new);

        Employees typewriter = employeesRepository
                .findById(dto.getEmployeeId())
                .orElseThrow(EntityNotFoundException::new);

        Date now = new Date();

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .typewriter(typewriter)
                        .calibrationStatus(calibrationStatus)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(now)
                        .build()
        );

        return result;
    }

    @Override
    public Calibration confirmByTypewriter(Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(5)
                .orElseThrow(EntityNotFoundException::new);
        Date now = new Date();

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .calibrationStatus(calibrationStatus)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(now)
                        .build()
        );

        return result;
    }


}
