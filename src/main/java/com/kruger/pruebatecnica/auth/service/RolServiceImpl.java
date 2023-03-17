package com.kruger.pruebatecnica.auth.service;

import com.kruger.pruebatecnica.auth.model.entity.Rol;

import com.kruger.pruebatecnica.auth.model.repository.RolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RolServiceImpl implements RolService{
    private final RolRepository rolRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
    @Override
    public Optional<Rol> getByRolName(String rolName) {
        return rolRepository.findByRolName(rolName);
    }

    @Override
    public void persist(Rol rol) {
        rolRepository.save(rol);
    }
}
