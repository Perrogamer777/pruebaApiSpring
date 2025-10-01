package com.example.apidatos.repository;

import com.example.apidatos.configuration.ParametrosZendesk;
import com.example.apidatos.entities.Logs;
import com.example.apidatos.structures.BodyTicket;
import com.example.apidatos.structures.Comment;
import com.example.apidatos.structures.Ticket;
import com.example.apidatos.structures.jsonRespuestaZendesk.ZendeskPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java .util.Base64;

@Service
public class CargaTicketsZendesk {
    private final ParametrosZendesk parametrosZendesk;
    private final RestTemplate restTemplate;
    private final LogsRepository logsRepository;

    @Autowired
    public CargaTicketsZendesk(ParametrosZendesk parametrosZendesk, RestTemplate restTemplate, LogsRepository logsRepository) {
        this.parametrosZendesk = parametrosZendesk;
        this.restTemplate = restTemplate;
        this.logsRepository = logsRepository;
    }
    // se ejecuta cada minuto
    //@Scheduled(cron = "0 * * * * ?")
    public void cargarTickets(){

        Long idLicitacionParaProcesar = 3L;

        // revisar la tabla logs si ya se procesó esta licitación.
        if (logsRepository.existsByIdLicitacion(idLicitacionParaProcesar)) {
            System.out.println("La licitación número: "+idLicitacionParaProcesar+" ya fue procesada.");
            return;
        }



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
            System.out.println("Enviando post a zendddd");
            ResponseEntity<String> response = restTemplate.postForEntity(urlZendesk, requestEntity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String jsonBody = response.getBody();
            ZendeskPayload payload = objectMapper.readValue(jsonBody, ZendeskPayload.class);
            System.out.println("Exitooooo. Status Code: " + response.getStatusCode());
            System.out.println("Respuesta del servidor: " + response.getBody());
            Logs log = Logs.builder()
                    .idLicitacion(idLicitacionParaProcesar)
                    .idTicket(payload.getTicket().getId())
                    .fechaIntentoOperacion(Timestamp.valueOf(LocalDateTime.now()))
                    .resultadoCodigoPeticion(response.getStatusCode().value())
                    .jsonEnviado(requestEntity.toString())
                    .jsonRecibido(jsonBody)
                    .build();
            logsRepository.saveAndFlush(log);
        }
        catch(Exception e){
            System.err.println("Error al realizar la petición: " + e.getMessage());
        }


    }
}