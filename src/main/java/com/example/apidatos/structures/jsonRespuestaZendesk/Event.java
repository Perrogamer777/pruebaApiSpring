package com.example.apidatos.structures.jsonRespuestaZendesk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    private long id;
    private String type;
    private String body;
    @JsonProperty("html_body")
    private String htmlBody;
    @JsonProperty("plain_body")
    private String plainBody;
    @JsonProperty("public")
    private boolean isPublic;
    private String value;
    @JsonProperty("field_name")
    private String fieldName;
}
