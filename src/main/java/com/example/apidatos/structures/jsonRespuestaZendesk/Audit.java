package com.example.apidatos.structures.jsonRespuestaZendesk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Audit {
    private long id;
    @JsonProperty("ticket_id")
    private long ticketId;
    @JsonProperty("created_at")
    private OffsetDateTime createdAt;
    @JsonProperty("author_id")
    private long authorId;
    private Metadata metadata;
    private List<Event> events;
    private Via via;
}
