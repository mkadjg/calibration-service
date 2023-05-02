package com.calibration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    String nip;
    String employeeName;
    String email;
    String phoneNumber;
    String address;
    int educationId;
    int jobPositionId;
    String username;
    String password;
}
