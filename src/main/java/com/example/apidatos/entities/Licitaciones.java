package com.example.apidatos.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "licitaciones")
public class Licitaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id_licitacion")
    private Long idLicitacion;
    @JsonProperty("id_cliente")
    private Long idCliente;
    @JsonProperty("id_sucursal")
    private Long idSucursal;
    private LocalDateTime fecha;
    @JsonProperty("fecha_limite")
    private LocalDateTime fechaLimite;
    @JsonProperty("unidades_cotizadas")
    private Integer unidadesCotizadas;
    @JsonProperty("unidades_adjudicadas")
    private Integer unidadesAdjudicadas;
}
