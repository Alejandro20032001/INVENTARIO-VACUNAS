package com.kruger.pruebatecnica.auth.model.repository;

import com.kruger.pruebatecnica.auth.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolName(String rolName);
}
