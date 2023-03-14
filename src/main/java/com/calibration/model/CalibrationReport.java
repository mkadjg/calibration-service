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
    int instrumentIndication;

    @Column(name = "standard_indication_up")
    int standardIndicationUp;

    @Column(name = "standard_indication_down")
    int standardIndicationDown;

    @Column(name = "correction_up")
    int correctionUp;

    @Column(name = "correction_down")
    int correctionDown;

    @ManyToOne
    @JoinColumn(name = "calibration_id", referencedColumnName = "calibration_id")
    Calibration calibration;


}
