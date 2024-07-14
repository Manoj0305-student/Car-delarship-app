package com.vw.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDto {

        private int customerId;

        @NotBlank(message = "Name is required")
        private String name;

        @Email(message = "Enter valid email!")
        private String email;

        @Pattern(regexp="(^$|[0-9]{10})", message = "Phone number must be 10 digits")
        private String phone;

        @NotBlank(message = "DL Number is required")
        private String dlNumber;

        private int executiveId;
}
