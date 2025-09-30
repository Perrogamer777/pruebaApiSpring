package com.example.apidatos.service;

import com.example.apidatos.entities.Licitaciones;
import com.example.apidatos.repository.LicitacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Service
public class LicitacionesService {

    @Autowired
    private LicitacionesRepository licitacionesRepository;

    public List<Licitaciones> getAllLicitaciones() {
        return licitacionesRepository.findAll();
    }

    public Licitaciones getLicitacionesPorId(Long idLicitaciones) {
        return licitacionesRepository.findById(idLicitaciones).orElse(null);
    }

    public List<Licitaciones> buscarPoridCliente(Long clienteId) {
        return licitacionesRepository.buscarPoridCliente(clienteId);
    }

}
