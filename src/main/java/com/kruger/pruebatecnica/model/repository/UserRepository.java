package com.kruger.pruebatecnica.model.repository;

import com.kruger.pruebatecnica.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
