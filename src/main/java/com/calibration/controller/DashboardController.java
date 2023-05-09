package com.calibration.controller;

import com.calibration.repository.CalibrationRepository;
import com.calibration.repository.ComplainRepository;
import com.calibration.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    DashboardService dashboardService;

    CalibrationRepository calibrationRepository;

    ComplainRepository complainRepository;

    DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ofPattern("MMMM, yyyy"))
            .toFormatter();

    DashboardController(DashboardService dashboardService,
                        CalibrationRepository calibrationRepository,
                        ComplainRepository complainRepository) {
        this.dashboardService = dashboardService;
        this.calibrationRepository = calibrationRepository;
        this.complainRepository = complainRepository;
    }

    @GetMapping("/find-actual-calibration-track/{customerId}")
    public Object findActualCalibrationId(@PathVariable int customerId) {
        return dashboardService.getActualCalibrationTrack(customerId);
    }

    @GetMapping("/find-actual-complain-track/{customerId}")
    public Object findActualComplainTrack(@PathVariable int customerId) {
        return dashboardService.getActualComplainTrack(customerId);
    }

    @GetMapping("/find-calibration-history/{customerId}")
    public Object findByCalibrationHistoryCustomerId(@PathVariable int customerId,
                                                     @RequestParam("period") String period) {
        LocalDate start = YearMonth.parse(period, dateTimeFormatter).atDay(1);
        LocalDate end   = YearMonth.parse(period, dateTimeFormatter).atEndOfMonth();
        return calibrationRepository.findHistoryByCustomerId(customerId, start, end);
    }

    @GetMapping("/find-complain-history/{customerId}")
    public Object findByComplainHistoryCustomerId(@PathVariable int customerId,
                                                  @RequestParam("period") String period) {
        LocalDate start = YearMonth.parse(period, dateTimeFormatter).atDay(1);
        LocalDate end   = YearMonth.parse(period, dateTimeFormatter).atEndOfMonth();
        return complainRepository.findHistoryByCustomerId(customerId, start, end);
    }

    @GetMapping("/find-customer-analysis-admin")
    public Object findCustomerAnalysisAdmin() {
        return dashboardService.getCustomerAnalysisAdmin();
    }

    @GetMapping("/find-customer-analysis-quality")
    public Object findCustomerAnalysisQuality() {
        return dashboardService.getCustomerAnalysisQuality();
    }

    @GetMapping("/find-customer-analysis-technician/{technicianId}")
    public Object findCustomerAnalysisTechnician(@PathVariable int technicianId) {
        return dashboardService.getCustomerAnalysisTechnician(technicianId);
    }

    @GetMapping("/find-customer-analysis-typewriter/{typewriterId}")
    public Object findCustomerAnalysisTypewriter(@PathVariable int typewriterId) {
        return dashboardService.getCustomerAnalysisTypewriter(typewriterId);
    }

}
