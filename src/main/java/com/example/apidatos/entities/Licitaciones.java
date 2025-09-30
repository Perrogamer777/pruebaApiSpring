package com.example.apidatos.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "licitaciones")
public class Licitaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_licitacion;
    @JsonProperty("id_cliente")
    @Column(name="id_cliente")
    private Long idCliente;
    private Long idSucursal;
    private String fecha;
    private Double precio;
    private String fecha_limite;
}
