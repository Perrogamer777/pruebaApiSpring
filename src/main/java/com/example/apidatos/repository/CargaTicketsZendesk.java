package com.example.apidatos.repository;

import com.example.apidatos.configuration.ParametrosZendesk;
import com.example.apidatos.structures.BodyTicket;
import com.example.apidatos.structures.Comment;
import com.example.apidatos.structures.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java .util.Base64;

@Service
public class CargaTicketsZendesk {
    private final ParametrosZendesk parametrosZendesk;
    private final RestTemplate restTemplate;

    @Autowired
    public CargaTicketsZendesk(ParametrosZendesk parametrosZendesk, RestTemplate restTemplate) {
        this.parametrosZendesk = parametrosZendesk;
        this.restTemplate = restTemplate;
    }
    // se ejecuta cada minuto
    @Scheduled(cron = "0 * * * * ?")
    public void cargarTickets(){
        System.out.println("Cargando tickets de Zendesk para el usuario: " + parametrosZendesk.getUsuario());
        String urlZendesk= "https://dimarsa-47694.zendesk.com/api/v2/tickets.json";

        HttpHeaders headers = new HttpHeaders();
        String auth = parametrosZendesk.getUsuario() + ":" + parametrosZendesk.getClave();
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);

        headers.set("Authorization", authHeader);
        headers.set("Content-Type", "application/json");

        BodyTicket body=new BodyTicket();
        Ticket ticket=new Ticket();
        Comment comment=new Comment();
        comment.setBody("Mi impresora no funciona");
        ticket.setComment(comment);
        ticket.setSubject("PrUEBA ticket desde api");
        ticket.setAssigneeId(41333829123611L);
        ticket.setGroupId(41333879112987L);
        ticket.setPriority("normal");
        body.setTicket(ticket);

        HttpEntity<BodyTicket> requestEntity = new HttpEntity<>(body, headers);

        try{
            System.out.println("Enviand post a zendddd");
            ResponseEntity<String> response = restTemplate.postForEntity(urlZendesk, requestEntity, String.class);

            System.out.println("Petición exitosa. Status Code: " + response.getStatusCode());
            System.out.println("Respuesta del servidor: " + response.getBody());
        }
        catch(Exception e){
            System.err.println("Error al realizar la petición: " + e.getMessage());
        }


    }
}
