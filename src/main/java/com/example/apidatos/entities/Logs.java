package com.example.apidatos.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "logs")
@Builder
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Integer id;
    // id ticket zendesk
    @Column(name = "id_ticket")
    private Long idTicket;

    @Column(name = "id_licitacion")
    private Long idLicitacion;

    @Column(name = "fecha_intento_operacion")
    private Timestamp fechaIntentoOperacion;

    @Column(name = "resultado_codigo_peticion")
    private Integer resultadoCodigoPeticion;

    @Column(name = "json_enviado", columnDefinition = "text")
    private String jsonEnviado;

    @Column(name = "json_recibido", columnDefinition = "text")
    private String jsonRecibido;

}
