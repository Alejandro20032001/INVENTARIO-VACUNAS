package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.entity.UserInformation;
import com.kruger.pruebatecnica.model.pojo.dto.RegisterUserDTO;
import com.kruger.pruebatecnica.model.pojo.dto.UpdateInformationDTO;
import com.kruger.pruebatecnica.model.pojo.dto.UserInformationDTO;
import com.kruger.pruebatecnica.model.pojo.vo.UserInformationVO;

import java.util.Optional;

public interface UserInformationService {
    Optional<UserInformationVO> findByIdVO(int id);
    Optional<UserInformation> findById(int id);
    UserInformation persistUserInformation(RegisterUserDTO registerUserDTO);
    void deleteUserInformation(int idUserInformation);
    UserInformationVO updateUserInformation(int idUserInformation, UpdateInformationDTO updateInformationDTO);

    UserInformationVO entityToVO (UserInformation userInformation);
}
