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
@Table(name = "calibration_method")
public class CalibrationMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calibration_method_id", nullable = false)
    int id;

    @Column(name = "calibration_method_code")
    String calibrationMethodCode;

    @Column(name = "calibration_method_name")
    String calibrationMethodName;

}
