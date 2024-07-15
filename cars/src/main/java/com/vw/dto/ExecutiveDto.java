package com.vw.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ExecutiveDto {

    private int executiveId;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Enter a Valid email!")
    private String email;

    private List<CustomerDto> customers;
}

