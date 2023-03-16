package com.kruger.pruebatecnica.model.pojo.vo;

import com.kruger.pruebatecnica.model.entity.Vaccine;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VaccinationVO {
    private Date vaccinationDate;
    private VaccineVO vaccineVO;
}
