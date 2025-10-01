package com.example.apidatos.structures.jsonRespuestaZendesk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SystemData {
    private String client;
    @JsonProperty("ip_address")
    private String ipAddress;
    private String location;
    private double latitude;
    private double longitude;
}