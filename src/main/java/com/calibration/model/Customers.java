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
@Table(name = "customers")
public class Customers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    int id;

    @Column(name = "company_name")
    String companyName;

    @Column(name = "pic_name")
    String picName;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "address", columnDefinition = "TEXT")
    String address;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    Users users;

}
