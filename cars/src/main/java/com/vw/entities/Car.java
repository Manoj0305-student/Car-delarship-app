package com.vw.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String brand;
	private int model;
	private String fuel;
	private String type;
	private String description;
	private String imgName;
	private String contentType;
	
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] imgData;
	
    @OneToMany(mappedBy = "car_id",cascade = CascadeType.ALL)
	private List<Appointment> appointmentList;
}
