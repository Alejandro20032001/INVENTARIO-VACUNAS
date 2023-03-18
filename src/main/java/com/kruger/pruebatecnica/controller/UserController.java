package com.kruger.pruebatecnica.controller;

import com.kruger.pruebatecnica.auth.model.entity.User;
import com.kruger.pruebatecnica.auth.service.UserService;
import com.kruger.pruebatecnica.commons.ResultResponse;
import com.kruger.pruebatecnica.model.pojo.dto.FilterDateDTO;
import com.kruger.pruebatecnica.model.pojo.dto.RegisterUserDTO;
import com.kruger.pruebatecnica.model.pojo.vo.UserVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/vo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findAllVO() {
        List<UserVO> lista = userService.findAllVO();
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(userService.findAllVO())
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(userService.findAll())
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @GetMapping("/vo/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findByIdVO(@PathVariable int id) {
        Optional<UserVO> user = userService.findByIdVO(id);
        if (user.isPresent())
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(true)
                    .message("Find successful")
                    .data(user.get())
                    .build(), ResponseEntity.ok().build().getStatusCode());
        return new ResponseEntity<>(ResultResponse.builder()
                .status(false)
                .message("Not found")
                .build(), ResponseEntity.notFound().build().getStatusCode());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent())
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(true)
                    .message("Find successful")
                    .data(user.get())
                    .build(), ResponseEntity.ok().build().getStatusCode());
        return new ResponseEntity<>(ResultResponse.builder()
                .status(false)
                .message("Not found")
                .build(), ResponseEntity.notFound().build().getStatusCode());
    }
    @GetMapping("/vaccinated")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findAllVaccinated() {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(userService.findAllVaccinated())
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @GetMapping("/non-vaccinated")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findAllNonVaccinated() {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(userService.findAllNotVaccinated())
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @GetMapping("/by-vaccine-name/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findAllByVaccineName(@PathVariable int id) {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(userService.findByVaccine(id))
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> save(@RequestBody RegisterUserDTO userDTO) {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(userService.persistUser(userDTO))
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent())
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(false)
                    .message("Not found")
                    .build(), ResponseEntity.notFound().build().getStatusCode());
        userService.deleteUser(id);
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Delete successful")
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @GetMapping("/by-date")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findAllByDate(@RequestBody FilterDateDTO filterDateDTO) {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .data(userService.dateFilter(filterDateDTO))
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
}
