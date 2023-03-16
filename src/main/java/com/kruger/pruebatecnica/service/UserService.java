package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.entity.User;
import com.kruger.pruebatecnica.model.pojo.dto.UserDTO;
import com.kruger.pruebatecnica.model.pojo.vo.UserVO;

import java.util.Optional;

public interface UserService {
    Optional<UserVO> findById(int id);
    Optional<UserVO> findAllVaccinated();
    Optional<UserVO> findAllNotVaccinated();
    Optional<UserVO> findByVaccine(int idVaccine);
    UserVO persistUser(UserDTO userDTO);
    void deleteUser(int idUser);
    UserVO entityToVO (User user);
}
