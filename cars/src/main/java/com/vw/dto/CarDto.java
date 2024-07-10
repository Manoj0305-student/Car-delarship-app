package com.vw.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

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

	public CarDto() {
		super();
	}

	public CarDto(int id, String name, String brand, int model, String fuel, String type, String description, String imageName, String content) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.fuel = fuel;
		this.type = type;
		this.description = description;
		this.imageName = imageName;
		this.content = content;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CarDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", brand='" + brand + '\'' +
				", model=" + model +
				", fuel='" + fuel + '\'' +
				", type='" + type + '\'' +
				", description='" + description + '\'' +
				", imageName='" + imageName + '\'' +
				", content=" + content +
				'}';
	}
}
