package com.calibration.controller;

import com.calibration.repository.JobPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job-position")
public class JobPositionController {

    JobPositionRepository jobPositionRepository;

    @Autowired
    JobPositionController(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return jobPositionRepository.findAll();
    }

}
