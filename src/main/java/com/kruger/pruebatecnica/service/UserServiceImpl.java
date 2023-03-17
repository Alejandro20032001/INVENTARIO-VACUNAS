package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.entity.User;
import com.kruger.pruebatecnica.model.entity.UserInformation;
import com.kruger.pruebatecnica.model.entity.Vaccination;
import com.kruger.pruebatecnica.model.entity.Vaccine;
import com.kruger.pruebatecnica.model.pojo.dto.UserDTO;
import com.kruger.pruebatecnica.model.pojo.vo.UserInformationVO;
import com.kruger.pruebatecnica.model.pojo.vo.UserVO;
import com.kruger.pruebatecnica.model.pojo.vo.VaccinationVO;
import com.kruger.pruebatecnica.model.pojo.vo.VaccineVO;
import com.kruger.pruebatecnica.model.repository.UserInformationRepository;
import com.kruger.pruebatecnica.model.repository.UserRepository;
import com.kruger.pruebatecnica.model.repository.VaccineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserInformationRepository userInformationRepository;
    private final VaccineRepository vaccineRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserInformationRepository userInformationRepository, VaccineRepository vaccineRepository) {
        this.userRepository = userRepository;
        this.userInformationRepository = userInformationRepository;
        this.vaccineRepository = vaccineRepository;
    }
    @Override
    public Optional<UserVO> findById(int id) {
        User user = userRepository.findById(id).get();
        if(user != null)
            return Optional.of(entityToVO(user));
        else
            return Optional.empty();
    }

    @Override
    public List<UserVO> findAllVaccinated() {
        List<Object[]> listUsers = userRepository.findAll();
        List<UserVO> listUserVO = new ArrayList<>();
        for(Object[] o: listUsers){
            if(o[7] == null) {
                UserVO userVO = objectToVO(o);
                listUserVO.add(userVO);
            }
        }
        return listUserVO;
    }

    @Override
    public List<UserVO> findAllNotVaccinated() {
        List<Object[]> listUsers = userRepository.findAll();
        List<UserVO> listUserVO = new ArrayList<>();
        for(Object[] o: listUsers){
            if(o[7] == null) {
                UserVO userVO = objectToVO(o);
                listUserVO.add(userVO);
            }
        }
        return listUserVO;
    }

    @Override
    public List<UserVO> findByVaccine(int idVaccine) {
        Vaccine vaccine = vaccineRepository.findById(idVaccine).get();
        String vaccineName = vaccine.getName();
        List<Object[]> listUsers = userRepository.findByVaccineName(vaccineName);
        List<UserVO> listUserVO = new ArrayList<>();
        for(Object[] o: listUsers){
            UserVO userVO = objectToVO(o);
            listUserVO.add(userVO);
        }
        return listUserVO;
    }
    @Override
    public UserVO persistUser(UserDTO userDTO) {
        User user = null;
        Optional <UserInformation> userInformation = userInformationRepository.findById(userDTO.getIdUserInformation());
        if(userInformation.isPresent()){
            user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setUserInformation(userInformation.get());
            user = userRepository.save(user);
        }
        return entityToVO(user);
    }
    @Override
    public void deleteUser(int idUser) {
        userRepository.deleteById(idUser);
    }

    @Override
    public UserVO entityToVO(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    private UserVO objectToVO(Object[] o){
        UserVO userVO = new UserVO();
        userVO.setUsername((String) o[0]);

        UserInformationVO userInformationVO = new UserInformationVO();
        userInformationVO.setAddress((String) o[1]);
        userInformationVO.setBirthDate((Date) o[2]);
        userInformationVO.setEmail((String) o[3]);
        userInformationVO.setLastName((String) o[4]);
        userInformationVO.setName((String) o[5]);
        userInformationVO.setPhone((String) o[6]);

        VaccinationVO vaccinationVO = new VaccinationVO();


        vaccinationVO.setVaccinationDate((Date) o[7]);

        VaccineVO vaccineVO = new VaccineVO();
        vaccineVO.setName((String) o[8]);

        vaccinationVO.setVaccineVO(vaccineVO);

        userInformationVO.setVaccinationVO(vaccinationVO);

        userVO.setUserInformationVO(userInformationVO);
        return userVO;
    }
}
