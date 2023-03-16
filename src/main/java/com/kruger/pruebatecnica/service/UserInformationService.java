package com.kruger.pruebatecnica.service;

import com.kruger.pruebatecnica.model.entity.UserInformation;
import com.kruger.pruebatecnica.model.pojo.dto.UserInformationDTO;
import com.kruger.pruebatecnica.model.pojo.vo.UserInformationVO;

import java.util.Optional;

public interface UserInformationService {
    Optional<UserInformationVO> findById(int id);
    UserInformationVO persistUserInformation(UserInformationDTO userInformationDTO);
    void deleteUserInformation(int idUserInformation);
    void updateUserInformation(UserInformationDTO userInformationDTO);
    UserInformationVO entityToVO (UserInformation userInformation);
}
