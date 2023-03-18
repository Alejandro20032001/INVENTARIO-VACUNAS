package com.kruger.pruebatecnica.controller;

import com.kruger.pruebatecnica.auth.model.entity.Rol;
import com.kruger.pruebatecnica.commons.ResultResponse;
import com.kruger.pruebatecnica.model.enums.HttpResponseMessage;
import com.kruger.pruebatecnica.model.pojo.dto.RegisterUserDTO;
import com.kruger.pruebatecnica.model.pojo.vo.UserVO;
import com.kruger.pruebatecnica.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/testing")
public class TestingController {
    private final UserService userService;
    @Autowired
    public TestingController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        //System.out.println(userService.findById(2));
        System.out.println(userService.findAllVaccinated());
        List<UserVO> list = userService.findAllVaccinated();
        ResponseEntity<?> responseEntity;
        if (list.size()==0)
            responseEntity =  new ResponseEntity<>(ResultResponse.builder()
                    .status(false)
                    .message(HttpResponseMessage.NOT_MATCH_FILTER.getValue())
                    .build(), HttpStatus.NOT_FOUND);
        else
            responseEntity =  new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message(HttpResponseMessage.FIND_SUCCESSFUL.getValue())
                .data(list)
                .build(), HttpStatus.OK);

        return responseEntity;
    }
    @GetMapping("/otro")
    public ResponseEntity<?> test2() {
        List<UserVO> list = userService.findByVaccine(1);
        ResponseEntity<?> responseEntity;
        if (list.size()==0)
            responseEntity =  new ResponseEntity<>(ResultResponse.builder()
                    .status(false)
                    .message(HttpResponseMessage.NOT_MATCH_FILTER.getValue())
                    .build(), HttpStatus.NOT_FOUND);
        else
            responseEntity =  new ResponseEntity<>(ResultResponse.builder()
                    .status(true)
                    .message(HttpResponseMessage.FIND_SUCCESSFUL.getValue())
                    .data(list)
                    .build(), HttpStatus.OK);

        return responseEntity;
    }
    @GetMapping("/otro2")
    public ResponseEntity<?> test3() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setName("Juan");
        registerUserDTO.setLastName("Perez");
        registerUserDTO.setEmail("email@gmail.com");
        registerUserDTO.setDni(9364973);

        Rol rol = new Rol();
        rol.setRolName("ADMIN");

        UserVO userVO = userService.persistUser(registerUserDTO);
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message(HttpResponseMessage.FIND_SUCCESSFUL.getValue())
                .data(userVO)
                .build(), HttpStatus.OK);
    }
}
