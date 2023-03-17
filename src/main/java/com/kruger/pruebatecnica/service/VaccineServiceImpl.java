package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.entity.Vaccine;
import com.kruger.pruebatecnica.model.pojo.dto.VaccineDTO;
import com.kruger.pruebatecnica.model.pojo.vo.VaccineVO;
import com.kruger.pruebatecnica.model.repository.VaccineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineServiceImpl implements VaccineService{
    private final VaccineRepository vaccineRepository;
    @Autowired
    public VaccineServiceImpl(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public List<Vaccine> findAll() {
        return vaccineRepository.findAll();
    }
    @Override
    public List<VaccineVO> findAllVO() {
        List<Vaccine> list = vaccineRepository.findAll();
        List<VaccineVO> listVO = new ArrayList<>();
        for (Vaccine vaccine : list) {
            listVO.add(entityToVO(vaccine));
        }
        return listVO;
    }
    @Override
    public Optional<VaccineVO> findByIdVO(int id) {
        Vaccine vaccine = vaccineRepository.findById(id).get();
        Optional optional = Optional.empty();
        if(vaccine != null)
            optional = Optional.of(entityToVO(vaccine));

        return optional;
    }
    @Override
    public Optional<Vaccine> findById(int id) {
        Vaccine vaccine = vaccineRepository.findById(id).get();
        Optional optional = Optional.empty();
        if(vaccine != null)
            optional = Optional.of(vaccine);

        return optional;
    }

    @Override
    public VaccineVO persistVaccine(VaccineDTO vaccineDTO) {
        Vaccine vaccine = new Vaccine();
        BeanUtils.copyProperties(vaccineDTO, vaccine);
        return entityToVO(vaccineRepository.save(vaccine));
    }

    @Override
    public void deleteVaccine(int idVaccine) {
        vaccineRepository.deleteById(idVaccine);
    }

    @Override
    public VaccineVO entityToVO(Vaccine vaccine) {
        VaccineVO vaccineVO = new VaccineVO();
        BeanUtils.copyProperties(vaccine, vaccineVO);
        return vaccineVO;
    }
}
