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
@Table(name = "traceable_to_si")
public class TraceableToSi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "traceable_to_si_id", nullable = false)
    int id;

    @Column(name = "traceable_to_si_code")
    String traceableToSiCode;

    @Column(name = "traceable_to_si_name")
    String traceableToSiName;

}
