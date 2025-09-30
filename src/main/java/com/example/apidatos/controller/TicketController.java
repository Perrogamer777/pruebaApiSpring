package com.example.apidatos.controller;

import com.example.apidatos.service.CargaTicketsZendesk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test-zendesk")
public class TicketController {

    @Autowired
    private CargaTicketsZendesk cargaTicketsZendesk;

    @PostMapping("/trigger")
    public ResponseEntity<String> testCrearTicket() {
        try {
            cargaTicketsZendesk.cargarTickets();
            return ResponseEntity.ok("listeilor.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("error en la ejecución: " + e.getMessage());
        }
    }
}