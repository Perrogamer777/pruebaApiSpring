package com.example.apidatos.controller;

import com.example.apidatos.entities.Licitaciones;
import com.example.apidatos.service.LicitacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/licitaciones")
public class LicitacionesController {

    @Autowired
    private LicitacionesService licitacionesService;

    @GetMapping
    public List<Licitaciones> getAllLicitaciones() {
        return licitacionesService.getAllLicitaciones();
    }

    @GetMapping("/{idLicitaciones}")
    public Licitaciones getLicitacionesPorId(@PathVariable Long idLicitaciones) {
        return licitacionesService.getLicitacionesPorId(idLicitaciones);
    }
}