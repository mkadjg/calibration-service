package com.calibration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "calibration")
public class Calibration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calibration_id", nullable = false)
    int id;

    @Column(name = "order_number")
    String orderNumber;

    @Column(name = "certificate_number")
    String certificateNumber;

    @Column(name = "receivedDate", columnDefinition = "DATE")
    LocalDate receivedDate;

    @Column(name = "calibration_location")
    String calibrationLocation;

    @Column(name = "calibration_date", columnDefinition = "DATE")
    LocalDate calibrationDate;

    @Column(name = "calibration_method")
    String calibrationMethod;

    @Column(name = "env_condition_t_before")
    Float envConditionTBefore;

    @Column(name = "env_condition_t_after")
    Float envConditionTAfter;

    @Column(name = "env_condition_rh_before")
    Float envConditionRhBefore;

    @Column(name = "env_condition_rh_after")
    Float envConditionRhAfter;

    @Column(name = "uncertainly")
    Float uncertainly;

    @Column(name = "standard_name")
    String standardName;

    @Column(name = "standard_type")
    String standardType;

    @Column(name = "standard_serial_number")
    String standardSerialNumber;

    @Column(name = "standard_traceable_to_si")
    String standardTraceableToSI;

    @Column(name = "issuance_date", columnDefinition = "DATE")
    LocalDate issuanceDate;

    @Column(name = "calibration_note")
    String calibrationNote;

    @Column(name = "confidence_level")
    Float confidenceLevel;

    @Column(name = "coverage_factor")
    Float coverageFactor;

    @Column(name = "is_assessed")
    Boolean isAssessed;

    @Column(name = "is_complain")
    Boolean isComplain;

    @ManyToOne
    @JoinColumn(name = "technician_id", referencedColumnName = "employee_id")
    Employees technician;

    @ManyToOne
    @JoinColumn(name = "typewriter_id", referencedColumnName = "employee_id")
    Employees typewriter;

    @ManyToOne
    @JoinColumn(name = "calibration_status_id", referencedColumnName = "calibration_status_id")
    CalibrationStatus calibrationStatus;

    @ManyToOne
    @JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id")
    Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "calibration_method_id", referencedColumnName = "calibration_method_id")
    CalibrationMethod calibrationMethode;

    @ManyToOne
    @JoinColumn(name = "traceable_to_si_id", referencedColumnName = "traceable_to_si_id")
    TraceableToSi traceableToSi;

    @ManyToOne
    @JoinColumn(name = "standard_calibration_type_id", referencedColumnName = "standard_calibration_type_id")
    StandardCalibrationType standardCalibrationType;


}
