package com.kruger.pruebatecnica.model.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VaccineDTO {
    @NotNull(message = "This field cannot be null")
    private String name;
}
