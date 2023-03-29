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
@Table(name = "calibration_report")
public class CalibrationReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calibration_report_id", nullable = false)
    int id;

    @Column(name = "instrument_indication")
    Integer instrumentIndication;

    @Column(name = "standard_indication_up")
    Integer standardIndicationUp;

    @Column(name = "standard_indication_down")
    Integer standardIndicationDown;

    @Column(name = "correction_up")
    Integer correctionUp;

    @Column(name = "correction_down")
    Integer correctionDown;

    @ManyToOne
    @JoinColumn(name = "calibration_id", referencedColumnName = "calibration_id")
    Calibration calibration;


}
