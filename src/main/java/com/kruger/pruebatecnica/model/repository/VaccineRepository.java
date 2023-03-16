package com.kruger.pruebatecnica.model.repository;

import com.kruger.pruebatecnica.model.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
}
