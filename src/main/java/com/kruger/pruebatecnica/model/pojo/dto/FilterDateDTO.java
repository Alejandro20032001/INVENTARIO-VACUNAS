package com.kruger.pruebatecnica.model.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FilterDateDTO {
    private Date startDate;
    private Date endDate;
}
