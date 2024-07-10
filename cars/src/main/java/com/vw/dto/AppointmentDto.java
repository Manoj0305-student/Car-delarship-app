package com.vw.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDto {
    private int id;
    private String customerName;
    private String customerContact;
    private Date appointmentDate;
    private String appointmentType;
    private String email;
    private int carId;
    private String dlNumber;
}
