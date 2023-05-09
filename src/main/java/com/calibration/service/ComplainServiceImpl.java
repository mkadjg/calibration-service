package com.calibration.service;

import com.calibration.dto.ComplainConfirmationDto;
import com.calibration.dto.ComplainForwardDto;
import com.calibration.dto.ComplainSubmissionDto;
import com.calibration.model.*;
import com.calibration.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ComplainServiceImpl implements ComplainService {

    ComplainRepository complainRepository;

    CalibrationRepository calibrationRepository;

    ComplainStatusRepository complainStatusRepository;

    ComplainTrackRepository complainTrackRepository;

    ComplainTypeRepository complainTypeRepository;

    EmployeesRepository employeesRepository;

    @Autowired
    ComplainServiceImpl(ComplainRepository complainRepository,
                        ComplainStatusRepository complainStatusRepository,
                        CalibrationRepository calibrationRepository,
                        ComplainTrackRepository complainTrackRepository,
                        ComplainTypeRepository complainTypeRepository,
                        EmployeesRepository employeesRepository) {
        this.complainRepository = complainRepository;
        this.complainStatusRepository = complainStatusRepository;
        this.calibrationRepository = calibrationRepository;
        this.complainTrackRepository = complainTrackRepository;
        this.complainTypeRepository = complainTypeRepository;
        this.employeesRepository = employeesRepository;
    }

    @Override
    public Complain create(Calibration calibration, ComplainSubmissionDto dto) {
        ComplainStatus complainStatus = complainStatusRepository.findById(1)
                        .orElseThrow(EntityNotFoundException::new);

        ComplainType complainType = complainTypeRepository.findById(dto.getComplainTypeId())
                .orElseThrow(EntityNotFoundException::new);

        Calibration result = calibrationRepository.save(calibration.toBuilder()
                .isComplain(true).build());

        Complain complain = complainRepository.save(Complain.builder()
                .calibration(result)
                .complainNote(dto.getComplainNote())
                .complainDate(LocalDate.now())
                .complainType(complainType)
                .complainStatus(complainStatus)
                .build());

        complainTrackRepository.save(
                ComplainTrack.builder()
                        .complainStatus(complainStatus)
                        .complain(complain)
                        .trackDate(LocalDateTime.now())
                        .build());

        return complain;
    }

    @Override
    public Complain confirm(ComplainConfirmationDto dto, Complain complain) {
        ComplainStatus complainStatus = complainStatusRepository.findById(2)
                .orElseThrow(EntityNotFoundException::new);

        Complain result = complainRepository.save(complain.toBuilder()
                .complainNumber(dto.getComplainNumber())
                .complainStatus(complainStatus)
                .build());

        complainTrackRepository.save(
                ComplainTrack.builder()
                        .complainStatus(complainStatus)
                        .complain(result)
                        .trackDate(LocalDateTime.now())
                        .build());

        return result;
    }

    @Override
    public Complain forward(ComplainForwardDto dto, Complain complain) throws ServiceUnavailableException {
        ComplainStatus complainStatus;
        Complain result;

        Employees employees = employeesRepository.findById(dto.getEmployeeId())
                .orElseThrow(EntityNotFoundException::new);

        switch (dto.getForwardTypeId()) {
            case 1: {
                complainStatus = complainStatusRepository.findById(3)
                        .orElseThrow(EntityNotFoundException::new);

                result = complainRepository.save(complain.toBuilder()
                        .technician(employees)
                        .complainStatus(complainStatus)
                        .build());
                break;
            }
            case 2: {
                complainStatus = complainStatusRepository.findById(6)
                        .orElseThrow(EntityNotFoundException::new);

                result = complainRepository.save(complain.toBuilder()
                        .typewriter(employees)
                        .complainStatus(complainStatus)
                        .build());
                break;
            }
            default: {
                throw new ServiceUnavailableException();
            }
        }
        complainTrackRepository.save(
                ComplainTrack.builder()
                        .complainStatus(complainStatus)
                        .complain(result)
                        .trackDate(LocalDateTime.now())
                        .build());
        return result;
    }

    @Override
    public Complain confirmByTechnician(Complain complain) {
        ComplainStatus complainStatus = complainStatusRepository.findById(4)
                .orElseThrow(EntityNotFoundException::new);

        Complain result = complainRepository.save(complain.toBuilder()
                .complainStatus(complainStatus)
                .build());

        complainTrackRepository.save(
                ComplainTrack.builder()
                        .complainStatus(complainStatus)
                        .complain(result)
                        .trackDate(LocalDateTime.now())
                        .build());

        return result;
    }

    @Override
    public Complain doneByTechnician(Complain complain) {
        ComplainStatus complainStatus = complainStatusRepository.findById(5)
                .orElseThrow(EntityNotFoundException::new);

        Complain result = complainRepository.save(complain.toBuilder()
                .complainStatus(complainStatus)
                .build());

        complainTrackRepository.save(
                ComplainTrack.builder()
                        .complainStatus(complainStatus)
                        .complain(result)
                        .trackDate(LocalDateTime.now())
                        .build());

        return result;
    }

    @Override
    public Complain confirmByTypewriter(Complain complain) {
        ComplainStatus complainStatus = complainStatusRepository.findById(7)
                .orElseThrow(EntityNotFoundException::new);

        Complain result = complainRepository.save(complain.toBuilder()
                .complainStatus(complainStatus)
                .build());

        complainTrackRepository.save(
                ComplainTrack.builder()
                        .complainStatus(complainStatus)
                        .complain(result)
                        .trackDate(LocalDateTime.now())
                        .build());

        return result;
    }

    @Override
    public Complain doneByTypewriter(Complain complain) {
        ComplainStatus complainStatus = complainStatusRepository.findById(8)
                .orElseThrow(EntityNotFoundException::new);

        Complain result = complainRepository.save(complain.toBuilder()
                .complainStatus(complainStatus)
                .build());

        complainTrackRepository.save(
                ComplainTrack.builder()
                        .complainStatus(complainStatus)
                        .complain(result)
                        .trackDate(LocalDateTime.now())
                        .build());

        return result;
    }

    @Override
    public Complain forwardToCustomer(Complain complain) {
        ComplainStatus complainStatus = complainStatusRepository.findById(9)
                .orElseThrow(EntityNotFoundException::new);

        Complain result = complainRepository.save(complain.toBuilder()
                .complainStatus(complainStatus)
                .build());

        complainTrackRepository.save(
                ComplainTrack.builder()
                        .complainStatus(complainStatus)
                        .complain(result)
                        .trackDate(LocalDateTime.now())
                        .build());

        return result;
    }

    @Override
    public Complain finishByCustomer(Complain complain) {
        ComplainStatus complainStatus = complainStatusRepository.findById(10)
                .orElseThrow(EntityNotFoundException::new);

        Complain result = complainRepository.save(complain.toBuilder()
                .complainStatus(complainStatus)
                .build());

        complainTrackRepository.save(
                ComplainTrack.builder()
                        .complainStatus(complainStatus)
                        .complain(result)
                        .trackDate(LocalDateTime.now())
                        .build());

        return result;
    }
}
