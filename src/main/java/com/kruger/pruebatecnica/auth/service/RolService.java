package com.kruger.pruebatecnica.auth.service;

import com.kruger.pruebatecnica.auth.model.entity.Rol;

import java.util.Optional;

public interface RolService {
    Optional<Rol> getByRolName(String rolName);
    void persist(Rol rol);
}
