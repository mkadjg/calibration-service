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
@Table(name = "complain")
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complain_id", nullable = false)
    int id;

    @Column(name = "complain_number")
    String complainNumber;

    @Column(name = "complain_note", columnDefinition = "TEXT")
    String complainNote;

    @ManyToOne
    @JoinColumn(name = "technician_id", referencedColumnName = "employee_id")
    Employees technician;

    @ManyToOne
    @JoinColumn(name = "typewriter_id", referencedColumnName = "employee_id")
    Employees typewriter;

    @ManyToOne
    @JoinColumn(name = "complain_status_id", referencedColumnName = "complain_status_id")
    ComplainStatus complainStatus;

    @ManyToOne
    @JoinColumn(name = "complain_type_id", referencedColumnName = "complain_type_id")
    ComplainType complainType;

    @ManyToOne
    @JoinColumn(name = "calibration_id", referencedColumnName = "calibration_id")
    Calibration calibration;

}
