package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.entity.UserInformation;
import com.kruger.pruebatecnica.model.entity.Vaccination;
import com.kruger.pruebatecnica.model.pojo.dto.UserInformationDTO;
import com.kruger.pruebatecnica.model.pojo.vo.UserInformationVO;
import com.kruger.pruebatecnica.model.repository.UserInformationRepository;
import com.kruger.pruebatecnica.model.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInformationServiceImpl implements UserInformationService{
    private final UserInformationRepository userInformationRepository;
    private final VaccinationRepository vaccinationRepository;
    @Autowired
    public UserInformationServiceImpl(UserInformationRepository userInformationRepository, VaccinationRepository vaccinationRepository) {
        this.userInformationRepository = userInformationRepository;
        this.vaccinationRepository = vaccinationRepository;
    }

    @Override
    public Optional<UserInformationVO> findByIdVO(int id) {
        UserInformation userInformation = userInformationRepository.findById(id).get();
        if (userInformation != null)
            return Optional.of(entityToVO(userInformation));
        else
            return Optional.empty();
    }
    @Override
    public Optional<UserInformation> findById(int id) {
        UserInformation userInformation = userInformationRepository.findById(id).get();
        if (userInformation != null)
            return Optional.of(userInformation);
        else
            return Optional.empty();
    }

    @Override
    public UserInformationVO persistUserInformation(UserInformationDTO userInformationDTO) {
        UserInformationVO userInformationVO = null;
        Optional<Vaccination> vaccination = vaccinationRepository.findById(userInformationDTO.getIdVaccination());
        if(vaccination.isPresent()) {
            UserInformation userInformation = new UserInformation();
            userInformation.setVaccination(vaccination.get());
            userInformation.setName(userInformationDTO.getName());
            userInformation.setLastName(userInformationDTO.getLastName());
            userInformation.setEmail(userInformationDTO.getEmail());
            userInformation.setBirthDate(userInformationDTO.getBirthDate());
            userInformation.setAddress(userInformationDTO.getAddress());
            userInformation.setPhone(userInformationDTO.getPhone());
            userInformation.setDni(userInformationDTO.getDni());
        }
        return userInformationVO;
    }

    @Override
    public void deleteUserInformation(int idUserInformation) {
        userInformationRepository.deleteById(idUserInformation);
    }

    @Override
    public void updateUserInformation(int idUserInformation, UserInformationDTO userInformationDTO) {
        Optional<UserInformation> userInformation = findById(idUserInformation);
        if(userInformation.isPresent()) {
            Optional<Vaccination> vaccination = vaccinationRepository.findById(userInformationDTO.getIdVaccination());
            userInformation.get().setName(userInformationDTO.getName());
            userInformation.get().setLastName(userInformationDTO.getLastName());
            userInformation.get().setEmail(userInformationDTO.getEmail());
            userInformation.get().setBirthDate(userInformationDTO.getBirthDate());
            userInformation.get().setAddress(userInformationDTO.getAddress());
            userInformation.get().setPhone(userInformationDTO.getPhone());
            userInformation.get().setDni(userInformationDTO.getDni());
            userInformation.get().setVaccination(vaccination.get());
            userInformationRepository.save(userInformation.get());
        }
    }
    @Override
    public UserInformationVO entityToVO(UserInformation userInformation) {
        return null;
    }
}
