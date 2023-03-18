package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.entity.Vaccination;
import com.kruger.pruebatecnica.model.pojo.dto.VaccinationDTO;
import com.kruger.pruebatecnica.model.pojo.vo.VaccinationVO;

import java.util.List;
import java.util.Optional;

public interface VaccinationService {
    List<Vaccination> findAll();

    List<VaccinationVO> findAllVO();

    Optional<VaccinationVO> findByIdVO(int id);
    Optional<Vaccination> findById(int id);
    VaccinationVO persistVaccination(VaccinationDTO vaccinationDTO);
    void deleteVaccination(int idVaccination);
    VaccinationVO entityToVO (Vaccination vaccination);
}
