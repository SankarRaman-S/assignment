package com.serand.assignment.model;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "candidates")
@Data
@Builder
public class Candidate {
    @Id
    private String id;
    private String name;
    List<Survey> surveys;

}
