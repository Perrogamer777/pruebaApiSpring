package com.example.apidatos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor // Requerido por JPA
@AllArgsConstructor // Requerido por @Builder
@Entity
@Table(name = "tickets_auxiliar")
public class TicketAuxiliar {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_ticket")
    // 3. SE USA camelCase para el nombre en Java
    private Long idTicket;

    @Column(name = "id_licitacion", nullable = false)
    private Long idLicitacion;

    @Column(name = "zendesk_ticket_id", nullable = false)
    private String zendeskTicketId;


    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}