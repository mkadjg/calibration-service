package com.calibration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "calibration_raw_data")
public class CalibrationRawData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calibration_raw_data_id", nullable = false)
    int id;

    @Column(name = "document")
    private byte[] document;

    @ManyToOne
    @JoinColumn(name = "calibration_id", referencedColumnName = "calibration_id")
    Calibration calibration;

}
