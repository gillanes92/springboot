
package com.todocodeacademy.clinica_veterinaria_api.service;

import java.util.List;

import com.todocodeacademy.clinica_veterinaria_api.model.Duenio;


public interface IDuenioService {
    
    //método para traer a todas las personas
    //lectura
    public List<Duenio> getDuenios();

    //alta
    public void saveDuenio(Duenio duenio);

    //baja
    public void deleteDuenio(Long id);

    //lectura de un solo objeto
    public Duenio findDuenio(Long id);

    //edición/modificación
    public void editDuenio (Duenio duenio);
    
}
