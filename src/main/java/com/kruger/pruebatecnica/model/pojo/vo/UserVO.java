package com.kruger.pruebatecnica.model.pojo.vo;

import com.kruger.pruebatecnica.model.entity.UserInformation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
    private String username;
    private UserInformationVO userInformationVO;
}
