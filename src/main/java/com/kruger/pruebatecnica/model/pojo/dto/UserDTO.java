package com.kruger.pruebatecnica.model.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    /*@NotNull(message = "This field cannot be null")
    private String username;

    @NotNull(message = "This field cannot be null")
    private String password;*/

    @NotNull(message = "This field cannot be null")
    private int idUserInformation;
}
