package com.kruger.pruebatecnica.model.repository;

import com.kruger.pruebatecnica.model.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {
}
