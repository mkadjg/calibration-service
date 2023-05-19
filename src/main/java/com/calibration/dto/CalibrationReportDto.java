package com.calibration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalibrationReportDto {
    Float uncertainly;
    Float confidenceLevel;
    Float coverageFactor;
    Float envConditionTBefore;
    Float envConditionTAfter;
    Float envConditionRhBefore;
    Float envConditionRhAfter;
    List<Report> result;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Report {
        int instrumentIndication;
        int standardIndicationUp;
        int standardIndicationDown;
        int correctionUp;
        int correctionDown;
    }

}
