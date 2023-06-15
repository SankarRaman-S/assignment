package com.serand.assignment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "jobs")
@Data
public class Job {

    @Id
    private String id;
    private String name;
    private String description;
    private List<Candidate> candidates;

}
