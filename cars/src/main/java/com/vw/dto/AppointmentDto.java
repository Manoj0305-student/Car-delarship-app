package com.vw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
