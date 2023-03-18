package com.kruger.pruebatecnica.controller;

import com.kruger.pruebatecnica.commons.ResultResponse;
import com.kruger.pruebatecnica.model.entity.Vaccine;
import com.kruger.pruebatecnica.model.pojo.dto.VaccineDTO;
import com.kruger.pruebatecnica.service.VaccineService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/vaccine")
public class VaccineController {
    private VaccineService vaccineService;
    @Autowired
    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping("/vo")
    @PreAuthorize("hasAuthority(['ADMIN', 'EMPLOYEE'])")
    public ResponseEntity<?> findAllVO() {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(vaccineService.findAllVO())
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @GetMapping("")
    @PreAuthorize("hasAuthority(['ADMIN', 'EMPLOYEE'])")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(vaccineService.findAll())
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> save(@RequestBody VaccineDTO dto) {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(vaccineService.persistVaccine(dto))
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @GetMapping("/vo/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<?> findByIdVO(@PathVariable int id) {
        Optional<Vaccine> vaccine = vaccineService.findById(id);
        if(vaccine.isPresent())
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(true)
                    .message("Find successful")
                    .data(vaccine.get())
                    .build(), ResponseEntity.ok().build().getStatusCode());
        return new ResponseEntity<>(ResultResponse.builder()
                .status(false)
                .message("Not found")
                .build(), ResponseEntity.notFound().build().getStatusCode());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Vaccine> vaccine = vaccineService.findById(id);
        if(vaccine.isPresent())
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(true)
                    .message("Find successful")
                    .data(vaccine.get())
                    .build(), ResponseEntity.ok().build().getStatusCode());
        return new ResponseEntity<>(ResultResponse.builder()
                .status(false)
                .message("Not found")
                .build(), ResponseEntity.notFound().build().getStatusCode());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional <Vaccine> vaccine = vaccineService.findById(id);
        if(!vaccine.isPresent())
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(false)
                    .message("Not found")
                    .build(), ResponseEntity.notFound().build().getStatusCode());
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Delete successful")
                .build(), ResponseEntity.ok().build().getStatusCode());
    }

}
