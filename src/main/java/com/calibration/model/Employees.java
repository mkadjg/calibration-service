package com.calibration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class Employees implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    int id;

    @Column(name = "nip")
    String nip;

    @Column(name = "employee_name")
    String employeeName;

    @Column(name = "address", columnDefinition = "TEXT")
    String address;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "job_position_id", referencedColumnName = "job_position_id")
    JobPosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "education_id", referencedColumnName = "education_id")
    Education education;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    Users users;
}
