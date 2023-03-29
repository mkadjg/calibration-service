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
@Table(name = "complain_track")
public class ComplainTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complain_track_id", nullable = false)
    int id;

    @Column(name = "track_date", columnDefinition = "DATE")
    Date trackDate;

    @ManyToOne
    @JoinColumn(name = "complain_id", referencedColumnName = "complain_id")
    Complain complain;

    @ManyToOne
    @JoinColumn(name = "complain_status_id", referencedColumnName = "complain_status_id")
    ComplainStatus complainStatus;
}
