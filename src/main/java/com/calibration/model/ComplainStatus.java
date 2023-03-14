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
@Table(name = "complain_status")
public class ComplainStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complain_status_id", nullable = false)
    int id;

    @Column(name = "complain_status_code")
    String calibrationStatusCode;

    @Column(name = "complain_status_name")
    String calibrationStatusName;

}
