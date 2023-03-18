package com.kruger.pruebatecnica.auth.service;

import com.kruger.pruebatecnica.auth.model.entity.User;
import com.kruger.pruebatecnica.auth.model.repository.UserRepository;
import com.kruger.pruebatecnica.model.entity.UserInformation;
import com.kruger.pruebatecnica.model.entity.Vaccine;
import com.kruger.pruebatecnica.model.pojo.dto.RegisterUserDTO;
import com.kruger.pruebatecnica.model.pojo.vo.UserInformationVO;
import com.kruger.pruebatecnica.model.pojo.vo.UserVO;
import com.kruger.pruebatecnica.model.pojo.vo.VaccinationVO;
import com.kruger.pruebatecnica.model.pojo.vo.VaccineVO;
import com.kruger.pruebatecnica.model.repository.UserInformationRepository;
import com.kruger.pruebatecnica.model.repository.VaccineRepository;
import com.kruger.pruebatecnica.service.UserInformationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserInformationRepository userInformationRepository;
    private final VaccineRepository vaccineRepository;
    private final UserInformationService userInformationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserInformationRepository userInformationRepository, VaccineRepository vaccineRepository, UserInformationService userInformationService) {
        this.userRepository = userRepository;
        this.userInformationRepository = userInformationRepository;
        this.vaccineRepository = vaccineRepository;
        this.userInformationService = userInformationService;
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public List<UserVO> findAllVO(){
        List<User> list = userRepository.findAll();
        List<UserVO> listVO = new ArrayList<>();
        for(User user: list){
            if(!user.getDelete())
                listVO.add(entityToVO(user));
        }
        return listVO;
    }
    @Override
    public Optional<User> findById(int id) {
        User user = userRepository.findById(id).get();
        if(user != null && !user.getDelete())
            return Optional.of(user);
        else
            return Optional.empty();
    }
    @Override
    public Optional<UserVO> findByIdVO(int id) {
        User user = userRepository.findById(id).get();
        if(user != null && !user.getDelete())
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
        Optional<Vaccine> vaccine = vaccineRepository.findById(idVaccine);
        if (vaccine.isPresent()) {
            List<Object[]> listUsers = userRepository.findByVaccineName(vaccine.get().getName());
            List<UserVO> listUserVO = new ArrayList<>();
            for (Object[] o : listUsers) {
                UserVO userVO = objectToVO(o);
                listUserVO.add(userVO);
            }
            return listUserVO;
        }

        return new ArrayList<>();
    }
    @Override
    public UserVO persistUser(RegisterUserDTO registerUserDTO) {
        UserInformation userInformation = userInformationService.persistUserInformation(registerUserDTO);

        User user = new User();
        String username = registerUserDTO.getName() +"_"+registerUserDTO.getLastName();
        String password = passwordGenerator();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String passwordBcrypt = passwordEncoder.encode(password);
        user.setUsername(username);
        user.setPassword(passwordBcrypt);
        user.setUserInformation(userInformation);
        user = userRepository.save(user);
        System.out.println("Password: "+password);
        user.setPassword(password);
        return entityToVO(user);
    }
    @Override
    public void deleteUser(int idUser) {
        User user = userRepository.findById(idUser).get();
        user.setDelete(true);
        userRepository.save(user);
    }

    @Override
    public UserVO entityToVO(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent())
            return user;
        else
            return Optional.empty();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
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
    public String passwordGenerator() {
        String[] symbols = {"_", "d", "-", "*", "%", "$", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
        int length = 10;
        Random random;
        try {
            random = SecureRandom.getInstanceStrong();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int indexRandom = random.nextInt ( symbols.length );
                sb.append( symbols[indexRandom] );
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e){
            System.out.println(e.toString());
        }
        return "";
    }
}
