package com.vw.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int appointmentId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDate;


    private String appointmentType;

    private boolean approved = false;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "carId")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "executiveId")
    private Executive executive;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

}
