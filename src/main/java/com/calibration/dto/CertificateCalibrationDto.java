package com.calibration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificateCalibrationDto {
    String certificateNumber;
    String calibrationMethod;
    String calibrationLocation;
    String issuanceDate;
    Integer envConditionTBefore;
    Integer envConditionTAfter;
    Integer envConditionRhBefore;
    Integer envConditionRhAfter;
    String standardName;
    String standardType;
    String standardSerialNumber;
    String standardTraceableToSi;
}
