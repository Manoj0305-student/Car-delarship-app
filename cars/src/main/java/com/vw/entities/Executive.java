package com.vw.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Executive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "executive_id")
    private int executiveId;
    private String name;

    @Email(message = "Enter a Valid email!")
    private String email;

    @OneToMany(mappedBy = "executive")
    @JsonManagedReference(value = "executiveAppointments")
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "executive")
    @JsonManagedReference(value ="executiveCustomers")
    private List<Customer> customers = new ArrayList<>();
}
