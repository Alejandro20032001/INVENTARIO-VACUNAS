package com.kruger.pruebatecnica.model.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VaccinationDTO {
    @NotNull(message = "This field cannot be null")
    private Date vaccinationDate;

    @NotNull(message = "This field cannot be null")
    private int idVaccine;

    @NotNull(message = "This field cannot be null")
    private int doseNumber;
}
