package com.kruger.pruebatecnica.controller;

import com.kruger.pruebatecnica.commons.ResultResponse;
import com.kruger.pruebatecnica.model.pojo.dto.VaccineDTO;
import com.kruger.pruebatecnica.service.VaccineService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/vaccine")
public class VaccineController {
    private VaccineService vaccineService;
    @Autowired
    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping("/all/vo")
    public ResponseEntity<?> findAllVO() {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(vaccineService.findAllVO())
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(vaccineService.findAll())
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody VaccineDTO dto) {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(vaccineService.persistVaccine(dto))
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @GetMapping("/vo/{id}")
    public ResponseEntity<?> findByIdVO(@PathVariable int id) {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(vaccineService.findByIdVO(id))
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Find successful")
                .data(vaccineService.findById(id))
                .build(), ResponseEntity.ok().build().getStatusCode());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        vaccineService.deleteVaccine(id);
        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message("Delete successful")
                .build(), ResponseEntity.ok().build().getStatusCode());
    }

}
