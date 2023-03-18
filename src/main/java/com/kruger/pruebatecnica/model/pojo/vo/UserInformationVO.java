package com.kruger.pruebatecnica.model.pojo.vo;

import com.kruger.pruebatecnica.model.entity.Vaccination;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserInformationVO {
    private String name;
    private String lastName;
    private String email;
    private Date birthDate;
    private String phone;
    private String address;
    private VaccinationVO vaccinationVO;
}
