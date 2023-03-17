package com.kruger.pruebatecnica.auth.model.repository;

import com.kruger.pruebatecnica.auth.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = """
            SELECT u.username,\s
            ui.address,\s
            ui.birth_date,\s
            ui.email,\s
            ui.last_name,\s
            ui.name,\s
            ui.phone,\s
            v.vaccination_date,\s
            va.name
            FROM public.user u \s
            LEFT JOIN user_information ui ON u.id_user_information = ui.id\s
            LEFT JOIN vaccination v ON ui.id_vaccination = v.id\s
            LEFT JOIN vaccine va ON v.id_vaccine = va.id\s
            WHERE u.delete = false AND ui.delete = false AND v.delete = false
            """, nativeQuery = true)
    List findAll();
    @Query(value = """
            SELECT u.username,\s
            ui.address,\s
            ui.birth_date,\s
            ui.email,\s
            ui.last_name,\s
            ui.name,\s
            ui.phone,\s
            v.vaccination_date,\s
            va.name
            FROM public.user u \s
            LEFT JOIN user_information ui ON u.id_user_information = ui.id \s
            LEFT JOIN vaccination v ON ui.id_vaccination = v.id \s
            LEFT JOIN vaccine va ON v.id_vaccine = va.id \s
            WHERE u.delete = false AND ui.delete = false AND v.delete = false
            AND va.name = :vaccineName""", nativeQuery = true)
    List findByVaccineName(@Param("vaccineName") String vaccineName);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
