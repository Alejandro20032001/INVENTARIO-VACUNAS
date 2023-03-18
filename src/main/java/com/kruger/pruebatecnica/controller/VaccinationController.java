package com.kruger.pruebatecnica.controller;

import com.kruger.pruebatecnica.commons.ResultResponse;
import com.kruger.pruebatecnica.model.entity.Vaccination;
import com.kruger.pruebatecnica.service.VaccinationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/vaccination")
public class VaccinationController {
    private VaccinationService vaccinationService;
    @Autowired
    public VaccinationController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(vaccinationService.findAll())
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @GetMapping("/vo")
    public ResponseEntity<?> findAllVO() {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(vaccinationService.findAllVO())
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @GetMapping("/vo/{id}")
    public ResponseEntity<?> findByIdVO(@PathVariable int id) {
        Optional<Vaccination> vaccination = vaccinationService.findById(id);
        if (vaccination.isPresent())
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(true)
                    .message("Find successful")
                    .data(vaccination.get())
                    .build(), ResponseEntity.ok().build().getStatusCode());
        return new ResponseEntity<>(ResultResponse.builder()
                .status(false)
                .message("Not found")
                .build(), ResponseEntity.notFound().build().getStatusCode());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Vaccination> vaccination = vaccinationService.findById(id);
        if (vaccination.isPresent())
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(true)
                    .message("Find successful")
                    .data(vaccination.get())
                    .build(), ResponseEntity.ok().build().getStatusCode());
        return new ResponseEntity<>(ResultResponse.builder()
                .status(false)
                .message("Not found")
                .build(), ResponseEntity.notFound().build().getStatusCode());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        Optional<Vaccination> vaccination = vaccinationService.findById(id);
        if (vaccination.isPresent()) {
            vaccinationService.deleteVaccination(id);
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(true)
                    .message("Delete successful")
                    .build(), ResponseEntity.ok().build().getStatusCode());
        }
        return new ResponseEntity<>(ResultResponse.builder()
                .status(false)
                .message("Not found")
                .build(), ResponseEntity.notFound().build().getStatusCode());
    }
}
