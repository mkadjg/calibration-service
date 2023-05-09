package com.calibration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "calibration_track")
public class CalibrationTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calibration_track_id", nullable = false)
    int id;

    @Column(name = "track_date", columnDefinition = "TIMESTAMP")
    LocalDateTime trackDate;

    @ManyToOne
    @JoinColumn(name = "calibration_id", referencedColumnName = "calibration_id")
    Calibration calibration;

    @ManyToOne
    @JoinColumn(name = "calibration_status_id", referencedColumnName = "calibration_status_id")
    CalibrationStatus calibrationStatus;
}
