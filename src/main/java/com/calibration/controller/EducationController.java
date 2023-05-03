package com.calibration.controller;

import com.calibration.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/education")
public class EducationController {

    EducationRepository educationRepository;

    @Autowired
    EducationController(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return educationRepository.findAll();
    }

}
