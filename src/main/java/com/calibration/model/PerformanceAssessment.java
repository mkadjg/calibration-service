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
@Table(name = "performance_assessment")
public class PerformanceAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_assessment_id", nullable = false)
    int id;

    @Column(name = "performance_assessment_note", columnDefinition = "TEXT")
    String performanceAssessmentNote;

    @Column(name = "rating")
    int rating;

    @ManyToOne
    @JoinColumn(name = "calibration_id", referencedColumnName = "calibration_id")
    Calibration calibration;

}
