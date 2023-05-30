package com.calibration.dto;

import com.calibration.model.Customers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerAnalysisDto {
    Customers customers;
    Integer count;
    Float averageRate;
}
