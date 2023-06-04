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
@Table(name = "standard_calibration_type")
public class StandardCalibrationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "standard_calibration_type_id", nullable = false)
    int id;

    @Column(name = "standard_calibration_type_code")
    String standardCalibrationTypeCode;

    @Column(name = "standard_calibration_type_name")
    String standardCalibrationTypeName;

}
