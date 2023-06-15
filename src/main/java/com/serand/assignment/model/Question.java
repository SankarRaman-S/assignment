package com.serand.assignment.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "question")
@Data
@Builder
public class Question {
    @Id
    private String id;
    private String question;
    private List<String> answers;
    private List<String> correctAnswers;
    private List<String> userAnswers;
    private Map<String,Integer> points;
}
