package com.kruger.pruebatecnica.model.pojo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateInformationDTO {
    @NotNull(message = "This field cannot be null")
    private Date birthDate;

    @NotNull(message = "This field cannot be null")
    @Min(value = 10000000, message = "This field must be a valid phone")
    @Max(value = 99999999, message = "This field must be a valid phone")
    private String phone;

    @NotNull(message = "This field cannot be null")
    private String address;

    @NotNull(message = "This field cannot be null")
    private Date vaccinationDate;

    @NotNull(message = "This field cannot be null")
    private int doseNumber;

    @NotNull(message = "This field cannot be null")
    private int idVaccine;
}
