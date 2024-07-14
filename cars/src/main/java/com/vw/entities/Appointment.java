package com.vw.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int appointmentId;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false,unique = true)
    private String customerContact;
    @Column(nullable = false)
    @Email(message = "Enter valid Email address")
    private String email;
    @Column(nullable = false)
    private Date appointmentDate;
    @Column()
    private String appointmentType; //test-drive or Buy
    @Column(nullable = false,unique = true)
    private String dlNumber;

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
