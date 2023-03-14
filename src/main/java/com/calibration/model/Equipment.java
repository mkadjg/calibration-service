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
@Table(name = "equipment")
public class Equipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id", nullable = false)
    int id;

    @Column(name = "serial_number")
    String serialNumber;

    @Column(name = "equipment_name")
    String equipmentName;

    @Column(name = "manufacturer")
    String manufacturer;

    @Column(name = "model_type")
    String modelType;

    @Column(name = "capacity")
    int capacity;

    @Column(name = "graduation")
    int graduation;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    Customers customers;


}
