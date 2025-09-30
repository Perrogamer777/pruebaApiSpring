package com.example.apidatos.controller;

import com.example.apidatos.entities.Cliente;
import com.example.apidatos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.apidatos.entities.Licitaciones;
import com.example.apidatos.service.LicitacionesService;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private LicitacionesService licitacionesService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/licitaciones/{idCliente}")
    public List<Licitaciones> getLicitacionesPorIdCliente(@PathVariable("idCliente")Long clienteId) {
        return licitacionesService.buscarPoridCliente(clienteId);
    }


}