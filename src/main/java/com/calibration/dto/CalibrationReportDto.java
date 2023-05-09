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
    int uncertainly;
    int confidenceLevel;
    int coverageFactor;
    int envConditionTBefore;
    int envConditionTAfter;
    int envConditionRhBefore;
    int envConditionRhAfter;
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
