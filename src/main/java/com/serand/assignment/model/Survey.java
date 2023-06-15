package com.serand.assignment.model;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "surveys")
@Data
@Builder
public class Survey {
    @Id
    private String id;
    private String name;
    private String companyId;
    private String jobId;
    private String candidateId;
    private String status;
    private long score;
    private List<Question> questions;

}
