package com.calibration.controller;

import com.calibration.repository.ComplainTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("complain-type")
public class ComplainTypeController {

    ComplainTypeRepository complainTypeRepository;

    @Autowired
    public ComplainTypeController(ComplainTypeRepository complainTypeRepository) {
        this.complainTypeRepository = complainTypeRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return complainTypeRepository.findAll();
    }
}
