package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.entity.Vaccination;
import com.kruger.pruebatecnica.model.pojo.dto.VaccinationDTO;
import com.kruger.pruebatecnica.model.pojo.vo.VaccinationVO;

import java.util.Optional;

public interface VaccinationService {
    Optional<VaccinationVO> findByIdVO(int id);
    Optional<Vaccination> findById(int id);
    VaccinationVO persistVaccination(VaccinationDTO vaccinationDTO);
    void deleteVaccination(int idVaccination);
    VaccinationVO entityToVO (Vaccination vaccination);
}
