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
    /**
     * Question details to be displayed
     */
    private String question;
    /**
     * Set of answers for the question
     */
    private List<String> answers;
    /**
     * Set of correct answers if case of multiples
     */
    private List<String> correctAnswers;
    /**
     * Set of user answers
     */
    private List<String> userAnswers;
    /**
     * Answer points to calculate score
     */
    private Map<String,Integer> points;
}
