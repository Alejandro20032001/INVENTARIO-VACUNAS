package com.kruger.pruebatecnica.model.pojo.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {
    @NotNull(message = "This field cannot be null")
    @Min(value = 1000000000, message = "This field must be a valid DNI")
    @Max(value = 9999999999L, message = "This field must be a valid DNI")
    private int dni;

    @NotNull(message = "This field cannot be null")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "This field must be a valid name")
    private String name;

    @NotNull(message = "This field cannot be null")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "This field must be a valid last name")
    private String lastName;

    @NotNull(message = "This field cannot be null")
    @Email(message = "This field must be a valid email")
    private String email;
}
