package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.entity.Vaccine;
import com.kruger.pruebatecnica.model.pojo.dto.VaccineDTO;
import com.kruger.pruebatecnica.model.pojo.vo.VaccineVO;

import java.util.List;
import java.util.Optional;

public interface VaccineService {
    List<VaccineVO> findAllVO();
    List<Vaccine> findAll();
    Optional<VaccineVO> findByIdVO(int id);
    Optional<Vaccine> findById(int id);
    VaccineVO persistVaccine(VaccineDTO vaccineDTO);
    void deleteVaccine(int idVaccine);
    VaccineVO entityToVO (Vaccine vaccine);
}
