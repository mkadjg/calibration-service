package com.calibration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "calibration_status")
public class CalibrationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calibration_status_id", nullable = false)
    int id;

    @Column(name = "calibration_status_code")
    String calibrationStatusCode;

    @Column(name = "calibration_status_name")
    String calibrationStatusName;
}
