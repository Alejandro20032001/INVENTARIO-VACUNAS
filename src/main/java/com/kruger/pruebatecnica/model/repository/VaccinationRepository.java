package com.kruger.pruebatecnica.model.repository;

import com.kruger.pruebatecnica.model.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepository extends JpaRepository<Vaccination, Integer> {
}
