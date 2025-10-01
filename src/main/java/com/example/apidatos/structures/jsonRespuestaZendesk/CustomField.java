package com.example.apidatos.structures.jsonRespuestaZendesk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomField {
    private long id;
    private Object value;
}