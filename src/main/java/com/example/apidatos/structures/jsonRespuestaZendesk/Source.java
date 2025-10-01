package com.example.apidatos.structures.jsonRespuestaZendesk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Source {
    private From from;
    private To to;
    private String rel;
}