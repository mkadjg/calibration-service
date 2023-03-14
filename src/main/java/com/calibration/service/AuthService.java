package com.calibration.service;

import com.calibration.dto.LoginDto;

public interface AuthService {
    Object authenticate(LoginDto dto);

}
