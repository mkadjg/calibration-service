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
import java.time.LocalDate;

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

        Calibration calibration = calibrationRepository.save(
                Calibration.builder()
                        .equipment(equipmentRepository.findById(dto.getEquipmentId())
                                .orElseThrow(EntityNotFoundException::new))
                        .calibrationNote(dto.getCalibrationNote())
                        .calibrationStatus(calibrationStatus)
                        .calibrationDate(LocalDate.now())
                .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(calibration)
                        .trackDate(LocalDate.now())
                        .build()
        );

        return calibration;
    }

    @Override
    public Calibration confirm(CalibrationConfirmationDto dto, Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(2)
                .orElseThrow(EntityNotFoundException::new);

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .orderNumber(dto.getOrderNumber())
                        .calibrationStatus(calibrationStatus)
                        .receivedDate(LocalDate.now())
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(LocalDate.now())
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

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .technician(technician)
                        .calibrationStatus(calibrationStatus)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(LocalDate.now())
                        .build()
        );

        return result;
    }

    @Override
    public Calibration confirmByTechnician(Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(4)
                .orElseThrow(EntityNotFoundException::new);

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .calibrationStatus(calibrationStatus)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(LocalDate.now())
                        .build()
        );

        return result;
    }

    @Override
    public Calibration doneByTechnician(Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(5)
                .orElseThrow(EntityNotFoundException::new);

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .calibrationStatus(calibrationStatus)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(LocalDate.now())
                        .build());

        return result;
    }

    @Override
    public Calibration forwardToTypewriter(CalibrationForwardDto dto, Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(6)
                .orElseThrow(EntityNotFoundException::new);

        Employees typewriter = employeesRepository
                .findById(dto.getEmployeeId())
                .orElseThrow(EntityNotFoundException::new);

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .typewriter(typewriter)
                        .calibrationStatus(calibrationStatus)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(LocalDate.now())
                        .build()
        );

        return result;
    }

    @Override
    public Calibration confirmByTypewriter(Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(7)
                .orElseThrow(EntityNotFoundException::new);

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .calibrationStatus(calibrationStatus)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(LocalDate.now())
                        .build()
        );

        return result;
    }

    @Override
    public Calibration doneByTypewriter(Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(8)
                .orElseThrow(EntityNotFoundException::new);

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .calibrationStatus(calibrationStatus)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(LocalDate.now())
                        .build());

        return result;
    }

    @Override
    public Calibration forwardToCustomer(Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(9)
                .orElseThrow(EntityNotFoundException::new);

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .calibrationStatus(calibrationStatus)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(LocalDate.now())
                        .build()
        );

        return result;
    }

    @Override
    public Calibration finishByCustomer(Calibration calibration) {
        CalibrationStatus calibrationStatus = calibrationStatusRepository
                .findById(10)
                .orElseThrow(EntityNotFoundException::new);

        Calibration result = calibrationRepository.save(
                calibration.toBuilder()
                        .calibrationStatus(calibrationStatus)
                        .build());

        calibrationTrackRepository.save(
                CalibrationTrack.builder()
                        .calibrationStatus(calibrationStatus)
                        .calibration(result)
                        .trackDate(LocalDate.now())
                        .build()
        );

        return result;
    }

}
