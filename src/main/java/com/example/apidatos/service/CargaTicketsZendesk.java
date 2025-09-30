package com.example.apidatos.service;

import com.example.apidatos.configuration.ParametrosZendesk;
import com.example.apidatos.entities.TicketAuxiliar;
import com.example.apidatos.repository.TicketAuxiliarRepository;
import com.example.apidatos.structures.BodyTicket;
import com.example.apidatos.structures.Comment;
import com.example.apidatos.structures.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class CargaTicketsZendesk {

    private static final Logger log = LoggerFactory.getLogger(CargaTicketsZendesk.class);

    private final ParametrosZendesk parametrosZendesk;
    private final RestTemplate restTemplate;
    private final TicketAuxiliarRepository ticketAuxiliarRepository;

    @Autowired
    public CargaTicketsZendesk(ParametrosZendesk parametrosZendesk, RestTemplate restTemplate, TicketAuxiliarRepository ticketAuxiliarRepository) {
        this.parametrosZendesk = parametrosZendesk;
        this.restTemplate = restTemplate;
        this.ticketAuxiliarRepository = ticketAuxiliarRepository;
    }

    // se ejecuta cada minuto
    @Scheduled(cron = "0 * * * * ?")
    public void cargarTickets(){
        log.info("Iniciando creación de ticket en Zendesk...");

        String idLicitacionParaProcesar = "LIC-" + System.currentTimeMillis(); // ID dinámico para cada prueba
        String urlZendesk= "https://dimarsa-47694.zendesk.com/api/v2/tickets.json";

        HttpHeaders headers = new HttpHeaders();
        String auth = parametrosZendesk.getUsuario() + ":" + parametrosZendesk.getClave();
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.set("Content-Type", "application/json");

        BodyTicket body=new BodyTicket();
        Ticket ticket=new Ticket();
        Comment comment=new Comment();
        comment.setBody("Mi impresora no funciona (Licitación: " + idLicitacionParaProcesar + ")");
        ticket.setComment(comment);
        ticket.setSubject("Prueba de ticket desde API para Licitación " + idLicitacionParaProcesar);
        ticket.setAssigneeId(41333829123611L);
        ticket.setGroupId(41333879112987L);
        ticket.setPriority("normal");
        body.setTicket(ticket);

        HttpEntity<BodyTicket> requestEntity = new HttpEntity<>(body, headers);

        try {
            log.info("Enviando petición POST a Zendesk...");
            ResponseEntity<String> response = restTemplate.postForEntity(urlZendesk, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Petición exitosa. Status Code: {}", response.getStatusCode());

                String respuestaJson = response.getBody();
                String zendeskId = extraerIdDeRespuesta(respuestaJson);

                if (zendeskId != null) {
                    TicketAuxiliar ticketParaGuardar = TicketAuxiliar.builder()
                            .idLicitacion(idLicitacionParaProcesar)
                            .zendeskTicketId(zendeskId)
                            .build();

                    ticketAuxiliarRepository.saveAndFlush(ticketParaGuardar);
                    log.info("Guardado Licitación {} -> Ticket Zendesk {}", idLicitacionParaProcesar, zendeskId);
                }
            } else {
                log.warn("La petición Zendesk no fue exitosa. Código: {}", response.getStatusCode());
            }
        } catch(Exception e){
            log.error("Error al realizar la petición a Zendesk: {}", e.getMessage(), e);
        }
    }

    private String extraerIdDeRespuesta(String respuestaJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(respuestaJson).path("ticket").path("id").asText();
        } catch (Exception e) {
            log.error("No se pudo extraer el ID del ticket desde el JSON: {}", respuestaJson, e);
            return null;
        }
    }
}