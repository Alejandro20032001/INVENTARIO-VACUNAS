package com.kruger.pruebatecnica.model.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserInformationDTO {
    private String name;
    private String lastName;
    private String email;
    private Date birthDate;
    private String phone;
    private String address;
    private int idVaccination;
}
