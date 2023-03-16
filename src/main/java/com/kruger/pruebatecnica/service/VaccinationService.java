package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.pojo.vo.VaccinationVO;

import java.util.Optional;

public interface VaccinationService {
    Optional<VaccinationVO> findById(int id);
    VaccinationVO persistVaccination(VaccinationVO vaccinationVO);
    void deleteVaccination(int idVaccination);
    VaccinationVO entityToVO (VaccinationVO vaccinationVO);
}
