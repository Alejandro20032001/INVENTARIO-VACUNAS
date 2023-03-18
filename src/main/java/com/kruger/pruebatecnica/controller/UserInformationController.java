package com.kruger.pruebatecnica.controller;

import com.kruger.pruebatecnica.commons.ResultResponse;
import com.kruger.pruebatecnica.model.entity.UserInformation;
import com.kruger.pruebatecnica.model.entity.Vaccination;
import com.kruger.pruebatecnica.model.pojo.dto.RegisterUserDTO;
import com.kruger.pruebatecnica.model.pojo.dto.UpdateInformationDTO;
import com.kruger.pruebatecnica.model.pojo.dto.UserInformationDTO;
import com.kruger.pruebatecnica.service.UserInformationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/userInformation")
public class UserInformationController {
    private UserInformationService userInformationService;
    @Autowired
    public UserInformationController(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @GetMapping("/vo/{id}")
    public ResponseEntity<?> findByIdVO(@PathVariable int id) {
        Optional<UserInformation> vaccination = userInformationService.findById(id);
        if (vaccination.isPresent())
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(true)
                    .message("Find successful")
                    .data(vaccination.get())
                    .build(), ResponseEntity.ok().build().getStatusCode());
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Not found")
                .build(), ResponseEntity.notFound().build().getStatusCode());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<UserInformation> vaccination = userInformationService.findById(id);
        if (vaccination.isPresent())
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(true)
                    .message("Find successful")
                    .data(vaccination.get())
                    .build(), ResponseEntity.ok().build().getStatusCode());
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Not found")
                .build(), ResponseEntity.notFound().build().getStatusCode());
    }
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody RegisterUserDTO registerUserDTO) {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Save successful")
                .data(userInformationService.persistUserInformation(registerUserDTO))
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody UpdateInformationDTO updateInformationDTO) {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Update successful")
                .data(userInformationService.updateUserInformation(id, updateInformationDTO))
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional <UserInformation> userInformation = userInformationService.findById(id);
        if(!userInformation.isPresent())
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(false)
                    .message("Not found")
                    .build(), ResponseEntity.notFound().build().getStatusCode());

        userInformationService.deleteUserInformation(id);
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Delete successful")
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
}
