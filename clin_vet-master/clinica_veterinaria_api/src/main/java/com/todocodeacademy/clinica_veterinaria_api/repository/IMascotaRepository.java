package com.todocodeacademy.clinica_veterinaria_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todocodeacademy.clinica_veterinaria_api.model.Mascota;

@Repository
public interface IMascotaRepository extends JpaRepository<Mascota, Long>{
    
}
