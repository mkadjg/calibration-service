package com.calibration.service;

import com.calibration.model.CalibrationTrack;
import com.calibration.model.ComplainTrack;

import java.util.List;

public interface DashboardService {
    List<CalibrationTrack> getActualCalibrationTrack(int customerId);

    List<ComplainTrack> getActualComplainTrack(int customerId);

    Object getCustomerAnalysisAdmin();

    Object getCustomerAnalysisTechnician(int technicianId);

    Object getCustomerAnalysisTypewriter(int typewriterId);

    Object getCustomerAnalysisQuality();

    Object getCustomerComplainAnalysisTechnician(int technicianId);

    Object getCustomerComplainAnalysisTypewriter(int typewriterId);
}
