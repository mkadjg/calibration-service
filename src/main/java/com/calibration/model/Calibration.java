package com.calibration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    Date receivedDate;

    @Column(name = "calibration_location")
    String calibrationLocation;

    @Column(name = "calibration_date", columnDefinition = "DATE")
    Date calibrationDate;

    @Column(name = "calibration_method")
    String calibrationMethod;

    @Column(name = "env_condition_t_before")
    Integer envConditionTBefore;

    @Column(name = "env_condition_t_after")
    Integer envConditionTAfter;

    @Column(name = "env_condition_rh_before")
    Integer envConditionRhBefore;

    @Column(name = "env_condition_rh_after")
    Integer envConditionRhAfter;

    @Column(name = "uncertainly")
    Integer uncertainly;

    @Column(name = "standard_name")
    String standardName;

    @Column(name = "standard_type")
    String standardType;

    @Column(name = "standard_serial_number")
    String standardSerialNumber;

    @Column(name = "standard_traceable_to_si")
    String standardTraceableToSI;

    @Column(name = "issuence_date", columnDefinition = "DATE")
    Date issuenceDate;

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


}
