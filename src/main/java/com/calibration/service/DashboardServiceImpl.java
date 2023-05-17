package com.calibration.service;

import com.calibration.dto.CustomerAnalysisDto;
import com.calibration.model.*;
import com.calibration.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    CalibrationRepository calibrationRepository;

    ComplainRepository complainRepository;

    CalibrationTrackRepository calibrationTrackRepository;

    ComplainTrackRepository complainTrackRepository;

    PerformanceAssessmentRepository performanceAssessmentRepository;

    @Autowired
    DashboardServiceImpl(CalibrationRepository calibrationRepository,
                         CalibrationTrackRepository calibrationTrackRepository,
                         ComplainRepository complainRepository,
                         ComplainTrackRepository complainTrackRepository,
                         PerformanceAssessmentRepository performanceAssessmentRepository) {
        this.calibrationRepository = calibrationRepository;
        this.complainRepository = complainRepository;
        this.calibrationTrackRepository = calibrationTrackRepository;
        this.complainTrackRepository = complainTrackRepository;
        this.performanceAssessmentRepository = performanceAssessmentRepository;
    }

    @Override
    public List<CalibrationTrack> getActualCalibrationTrack(int customerId) {
        List<Calibration> calibrations = calibrationRepository.findInProgressCalibration(customerId);
        if (!calibrations.isEmpty()) {
            return calibrationTrackRepository.findByCalibrationId(calibrations.get(0).getId());
        }
        return new ArrayList<>();
    }

    @Override
    public List<ComplainTrack> getActualComplainTrack(int customerId) {
        List<Complain> complains = complainRepository.findInProgressComplain(customerId);
        if (!complains.isEmpty()) {
            return complainTrackRepository.findByComplainId(complains.get(0).getId());
        }
        return new ArrayList<>();
    }

    @Override
    public Object getCustomerAnalysisAdmin() {
        List<Customers> customers = calibrationRepository.findAllCustomer();
        return customers.stream().map(customer ->
            CustomerAnalysisDto.builder()
                    .customers(customer)
                    .count(calibrationRepository.countCalibrationByCustomerId(customer.getId()))
                    .averageRate(performanceAssessmentRepository.averageRateByCustomerId(customer.getId()))
                    .build()
        ).collect(Collectors.toList());
    }

    @Override
    public Object getCustomerAnalysisTechnician(int technicianId) {
        List<Customers> customers = calibrationRepository.findAllCustomerByTechnician(technicianId);
        return customers.stream().map(customer ->
                CustomerAnalysisDto.builder()
                        .customers(customer)
                        .count(calibrationRepository.countCalibrationByCustomerIdAndTechnicianId(customer.getId(), technicianId))
                        .averageRate(performanceAssessmentRepository.averageRateByCustomerIdAndTechnicianId(customer.getId(), technicianId))
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public Object getCustomerAnalysisTypewriter(int typewriterId) {
        List<Customers> customers = calibrationRepository.findAllCustomerByTypewriter(typewriterId);
        return customers.stream().map(customer ->
                CustomerAnalysisDto.builder()
                    .customers(customer)
                    .count(calibrationRepository.countCalibrationByCustomerIdAndTypewriterId(customer.getId(), typewriterId))
                    .averageRate(performanceAssessmentRepository.averageRateByCustomerIdAndTypewriterId(customer.getId(), typewriterId))
                    .build()
        ).collect(Collectors.toList());
    }

    @Override
    public Object getCustomerAnalysisQuality() {
        List<Customers> customers = complainRepository.findAllCustomer();
        return customers.stream().map(customer ->
                CustomerAnalysisDto.builder()
                    .customers(customer)
                    .count(complainRepository.countComplainByCustomerId(customer.getId()))
                    .build()
        ).collect(Collectors.toList());
    }

    @Override
    public Object getCustomerComplainAnalysisTechnician(int technicianId) {
        List<Customers> customers = complainRepository.findAllCustomer();
        return customers.stream().map(customer ->
                CustomerAnalysisDto.builder()
                    .customers(customer)
                    .count(complainRepository.countComplainByCustomerIdAndTechnicianId(customer.getId(), technicianId))
                    .build()
        ).collect(Collectors.toList());
    }

    @Override
    public Object getCustomerComplainAnalysisTypewriter(int typewriterId) {
        List<Customers> customers = complainRepository.findAllCustomer();
        return customers.stream().map(customer ->
                CustomerAnalysisDto.builder()
                    .customers(customer)
                    .count(complainRepository.countComplainByCustomerIdAndTypewriterId(customer.getId(), typewriterId))
                    .build()
        ).collect(Collectors.toList());
    }
}
