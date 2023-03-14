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
@Table(name = "complain_type")
public class ComplainType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complain_type_id", nullable = false)
    int id;

    @Column(name = "complain_type_code")
    String calibrationStatusCode;

    @Column(name = "complain_type_name")
    String calibrationStatusName;

}
