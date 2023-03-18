package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.entity.UserInformation;
import com.kruger.pruebatecnica.model.entity.Vaccination;
import com.kruger.pruebatecnica.model.entity.Vaccine;
import com.kruger.pruebatecnica.model.pojo.dto.RegisterUserDTO;
import com.kruger.pruebatecnica.model.pojo.dto.UpdateInformationDTO;
import com.kruger.pruebatecnica.model.pojo.vo.UserInformationVO;
import com.kruger.pruebatecnica.model.repository.UserInformationRepository;
import com.kruger.pruebatecnica.model.repository.VaccinationRepository;
import com.kruger.pruebatecnica.model.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInformationServiceImpl implements UserInformationService{
    private final UserInformationRepository userInformationRepository;
    private final VaccinationRepository vaccinationRepository;
    private final VaccineRepository vaccineRepository;
    @Autowired
    public UserInformationServiceImpl(UserInformationRepository userInformationRepository, VaccinationRepository vaccinationRepository, VaccineRepository vaccineRepository) {
        this.userInformationRepository = userInformationRepository;
        this.vaccinationRepository = vaccinationRepository;
        this.vaccineRepository = vaccineRepository;
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
    public UserInformation persistUserInformation(RegisterUserDTO registerUserDTO) {

        UserInformation userInformation = new UserInformation();
        userInformation.setName(registerUserDTO.getName());
        userInformation.setLastName(registerUserDTO.getLastName());
        userInformation.setEmail(registerUserDTO.getEmail());
        userInformation.setDni(registerUserDTO.getDni());

        userInformationRepository.save(userInformation);
        /*
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
        }*/
        return userInformation;
    }

    @Override
    public void deleteUserInformation(int idUserInformation) {
        userInformationRepository.deleteById(idUserInformation);
    }

    @Override
    public UserInformationVO updateUserInformation(int idUserInformation, UpdateInformationDTO updateInformationDTO) {
        Optional<UserInformation> userInformation = findById(idUserInformation);
        Optional<Vaccine> vaccine = vaccineRepository.findById(updateInformationDTO.getIdVaccine());
        if(userInformation.isPresent()) {
            Vaccination vaccination = new Vaccination();
            vaccination.setDoseNumber(updateInformationDTO.getDoseNumber());
            vaccination.setVaccinationDate(updateInformationDTO.getVaccinationDate());
            if(vaccine.isPresent())
                vaccination.setVaccine(vaccine.get());

            userInformation.get().setBirthDate(updateInformationDTO.getBirthDate());
            userInformation.get().setAddress(updateInformationDTO.getAddress());
            userInformation.get().setPhone(updateInformationDTO.getPhone());
            userInformation.get().setVaccination(vaccination);
            userInformationRepository.save(userInformation.get());
        }
        return entityToVO(userInformation.get());
    }
    @Override
    public UserInformationVO entityToVO(UserInformation userInformation) {
        return null;
    }
}
