package com.vw.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;
    private String name;

    @Email(message = "Enter valid email!")
    private String email;
    private String phone;

    private String dlNumber;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference(value ="customerAppointments")
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "executiveId")
    @JsonBackReference(value ="executiveCustomers")
    private Executive executive;

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Car> cars;
}
