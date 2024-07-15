package com.vw.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vw.entities.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
	
	private int id;
	private String name;
	private String brand;
	private int model;
	private String fuel;
	private String type;
	private String description;
	private String imageName;
	private String content;
	private double price;
	private List<Appointment> appointmentDtoList;
	private CustomerDto customer;
}
