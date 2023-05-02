package com.calibration.controller;

import com.calibration.repository.ComplainTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/complain-track")
public class ComplainTrackController {

    ComplainTrackRepository complainTrackRepository;

    @Autowired
    ComplainTrackController(ComplainTrackRepository complainTrackRepository) {
        this.complainTrackRepository = complainTrackRepository;
    }

    @GetMapping("/find-by-complain-id/{complainId}")
    public Object findByComplainId(@PathVariable int complainId) {
        return complainTrackRepository.findByComplainId(complainId);
    }

}
