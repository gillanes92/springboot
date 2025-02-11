package com.todocodeacademy.clinica_veterinaria_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todocodeacademy.clinica_veterinaria_api.model.Duenio;

@Repository
public interface IDuenioRepository extends JpaRepository <Duenio, Long>{
    
}
