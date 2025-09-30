package com.example.apidatos.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "app.parametros-zendesk")
@Configuration
public class ParametrosZendesk {
    private String usuario;
    private String clave;
}

