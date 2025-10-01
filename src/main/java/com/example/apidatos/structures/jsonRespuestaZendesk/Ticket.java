package com.example.apidatos.structures.jsonRespuestaZendesk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
    private long id;
    private String url;
    @JsonProperty("external_id")
    private Object externalId;
    private Via via;
    @JsonProperty("created_at")
    private OffsetDateTime createdAt;
    @JsonProperty("updated_at")
    private OffsetDateTime updatedAt;
    private String subject;
    private String description;
    private String priority;
    private String status;
    @JsonProperty("requester_id")
    private long requesterId;
    @JsonProperty("submitter_id")
    private long submitterId;
    @JsonProperty("assignee_id")
    private long assigneeId;
    @JsonProperty("organization_id")
    private long organizationId;
    @JsonProperty("group_id")
    private long groupId;
    private List<String> tags;
    @JsonProperty("custom_fields")
    private List<CustomField> customFields;
    @JsonProperty("satisfaction_rating")
    private SatisfactionRating satisfactionRating;
    @JsonProperty("brand_id")
    private long brandId;
}