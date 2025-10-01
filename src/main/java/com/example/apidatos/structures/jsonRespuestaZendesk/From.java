package com.example.apidatos.structures.jsonRespuestaZendesk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class From {
    // Clase para el objeto "from", puede estar vacía o tener campos
}