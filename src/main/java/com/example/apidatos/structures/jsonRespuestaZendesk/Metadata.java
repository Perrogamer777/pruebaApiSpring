package com.example.apidatos.structures.jsonRespuestaZendesk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {
    private SystemData system;
    private Object custom;
}
