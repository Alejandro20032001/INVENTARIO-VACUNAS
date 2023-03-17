package com.kruger.pruebatecnica.auth.service;

import com.kruger.pruebatecnica.auth.model.entity.User;
import com.kruger.pruebatecnica.model.pojo.dto.UserDTO;
import com.kruger.pruebatecnica.model.pojo.vo.UserVO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserVO> findById(int id);
    List<UserVO> findAllVaccinated();
    List<UserVO> findAllNotVaccinated();
    List<UserVO> findByVaccine(int idVaccine);
    UserVO persistUser(UserDTO userDTO);
    void deleteUser(int idUser);
    UserVO entityToVO (User user);
    Optional <User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
