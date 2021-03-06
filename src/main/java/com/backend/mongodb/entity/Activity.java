package com.backend.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "activities")
public class Activity extends Timestamp {
    @Id
    private String id;

    @JsonProperty("activity_name")
    private String activityName;

    @JsonProperty("activity_description")
    private String activityDescription;

    @JsonProperty("agent")
    private String agent;

    public Activity(String activityName, String activityDescription, String agent) {
        this.activityName = activityName;
        this.activityDescription = activityDescription;
        this.agent = agent;
    }
}
