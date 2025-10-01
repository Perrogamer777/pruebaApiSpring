package com.example.apidatos.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets_auxiliar")
public class TicketAuxiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id_ticket")
    private Long idTicket;
    @JsonProperty("id_licitacion")
    private Long idLicitacion;
    @JsonProperty("zendesk_ticket_id")
    private Long zendeskTicketId;
    @JsonProperty("fecha_creacion")
    private LocalDateTime fechaCreacion;

}