package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.entity.Vaccine;
import com.kruger.pruebatecnica.model.pojo.vo.VaccineVO;

import java.util.Optional;

public interface VaccineService {
    Optional<VaccineVO> findById(int id);
    VaccineVO persistVaccine(VaccineVO vaccineVO);
    void deleteVaccine(int idVaccine);
    VaccineVO entityToVO (Vaccine vaccine);
}
