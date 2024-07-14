package com.vw.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Executive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "executive_id")
    private Long executiveId;
    private String name;
    private String email;

    @OneToMany(mappedBy = "executive")
    @JsonManagedReference
    private List<Appointment> appointments = new ArrayList<>();


}