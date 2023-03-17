package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.entity.Vaccination;
import com.kruger.pruebatecnica.model.entity.Vaccine;
import com.kruger.pruebatecnica.model.pojo.dto.VaccinationDTO;
import com.kruger.pruebatecnica.model.pojo.vo.VaccinationVO;
import com.kruger.pruebatecnica.model.repository.VaccinationRepository;
import com.kruger.pruebatecnica.model.repository.VaccineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VaccinationServiceImpl implements VaccinationService {
    private final VaccinationRepository vaccinationRepository;
    private final VaccineRepository vaccineRepository;
    @Autowired
    public VaccinationServiceImpl(VaccinationRepository vaccinationRepository, VaccineRepository vaccineRepository) {
        this.vaccinationRepository = vaccinationRepository;
        this.vaccineRepository = vaccineRepository;
    }
    @Override
    public Optional<VaccinationVO> findByIdVO(int id) {
        Vaccination vaccination = vaccinationRepository.findById(id).get();
        if (vaccination != null)
            return Optional.of(entityToVO(vaccination));
        else
            return Optional.empty();

    }

    @Override
    public Optional<Vaccination> findById(int id) {
        Vaccination vaccination = vaccinationRepository.findById(id).get();
        if (vaccination != null)
            return Optional.of(vaccination);
        else
            return Optional.empty();
    }
    @Override
    public VaccinationVO persistVaccination(VaccinationDTO vaccinationDTO) {
        VaccinationVO vaccinationVO = null;
        Optional<Vaccine> vaccine= vaccineRepository.findById(vaccinationDTO.getIdVaccine());
        if(vaccine.isPresent()) {
            Vaccination vaccination = new Vaccination();
            vaccination.setVaccine(vaccine.get());
            vaccination.setVaccinationDate(vaccinationDTO.getVaccinationDate());
            vaccination.setDoseNumber(vaccinationDTO.getDoseNumber());
            vaccinationVO = entityToVO(vaccinationRepository.save(vaccination));
        }
        return vaccinationVO;
    }

    @Override
    public void deleteVaccination(int idVaccination) {
        vaccinationRepository.deleteById(idVaccination);
    }

    @Override
    public VaccinationVO entityToVO(Vaccination vaccination) {
        VaccinationVO vaccinationVO = new VaccinationVO();
        BeanUtils.copyProperties(vaccination, vaccinationVO);
        return vaccinationVO;
    }
}
