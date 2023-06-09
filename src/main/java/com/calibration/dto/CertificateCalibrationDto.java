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
    Integer calibrationMethodId;
    String calibrationLocation;
    String issuanceDate;
    String standardName;
    String standardType;
    Integer standardTypeId;
    String standardSerialNumber;
    String standardTraceableToSI;
    Integer standardTraceableToSIId;
}
