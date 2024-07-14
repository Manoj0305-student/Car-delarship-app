package com.vw.dto;

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
	private List<Appointment> appointmentDtoList;

	public CarDto(int i, String car1, String audi, int i1, String petrol, String hybrid, String description, String image, List<AppointmentDto> appointmentDtoList) {
	}
}
