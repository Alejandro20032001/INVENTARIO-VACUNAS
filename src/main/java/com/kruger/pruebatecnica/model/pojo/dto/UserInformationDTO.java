package com.kruger.pruebatecnica.model.pojo.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserInformationDTO {
    @NotNull(message = "This field cannot be null")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "This field must be a valid name")
    private String name;

    @NotNull(message = "This field cannot be null")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "This field must be a valid last name")
    private String lastName;

    @NotNull(message = "This field cannot be null")
    @Email(message = "This field must be a valid email")
    private String email;

    @NotNull(message = "This field cannot be null")
    private Date birthDate;

    @NotNull(message = "This field cannot be null")
    @Min(value = 10000000, message = "This field must be a valid phone")
    @Max(value = 99999999, message = "This field must be a valid phone")
    private String phone;

    @NotNull(message = "This field cannot be null")
    private String address;

    @NotNull(message = "This field cannot be null")
    private int idVaccination;

    @NotNull(message = "This field cannot be null")
    @Min(value = 1000000000, message = "This field must be a valid DNI")
    @Max(value = 9999999999L, message = "This field must be a valid DNI")
    private int dni;
}
