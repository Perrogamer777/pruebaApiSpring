package com.example.apidatos.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Ticket {
    private String subject;
    private Comment comment;
    private String priority;
    @JsonProperty("assignee_id")
    private Long assigneeId;
    @JsonProperty("group_id")
    private Long groupId;
}
