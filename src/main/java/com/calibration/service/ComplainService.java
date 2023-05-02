package com.calibration.service;

import com.calibration.dto.ComplainConfirmationDto;
import com.calibration.dto.ComplainForwardDto;
import com.calibration.dto.ComplainSubmissionDto;
import com.calibration.model.Calibration;
import com.calibration.model.Complain;

import javax.naming.ServiceUnavailableException;

public interface ComplainService {
    Complain create(Calibration calibration, ComplainSubmissionDto dto);
    Complain confirm(ComplainConfirmationDto dto, Complain complain);
    Complain forward(ComplainForwardDto dto, Complain complain) throws ServiceUnavailableException;
    Complain confirmByTechnician(Complain complain);
    Complain doneByTechnician(Complain complain);
    Complain confirmByTypewriter(Complain complain);
    Complain doneByTypewriter(Complain complain);
    Complain forwardToCustomer(Complain complain);
    Complain finishByCustomer(Complain complain);
}
