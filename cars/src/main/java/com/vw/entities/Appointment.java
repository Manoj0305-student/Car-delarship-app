package com.vw.entities;

import jakarta.persistence.*;
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
    private String email;
    @Column(nullable = false) //I need to map this
    private Date appointmentDate;
    @Column()
    private String appointmentType; //test-drive or Buy
    @Column(nullable = false,unique = true)
    private String dlNumber;



    @ManyToOne
    @JoinColumn(name = "id")
    private Car car;


}
