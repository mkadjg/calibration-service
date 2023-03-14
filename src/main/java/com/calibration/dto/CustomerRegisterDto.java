package com.calibration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegisterDto {

    // customer
    String companyName;
    String picName;
    String email;
    String phoneNumber;
    String address;

    // user
    String username;
    String password;
}
