package com.serand.assignment.controller;

import com.serand.assignment.model.Question;
import com.serand.assignment.model.Survey;
import com.serand.assignment.service.SurveyService;
import com.serand.assignment.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/surveys")
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    @PostMapping()
    public ResponseEntity<List<Survey>> create(@RequestBody Survey survey) {

        survey.setScore(Util.calculateScore(survey.getQuestions()));

        List<Survey> savedSurvey = surveyService.create(survey);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{surveyId}").build(savedSurvey.stream().findFirst().get().getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(surveyService.create(survey));
    }

    @GetMapping("/")
    public ResponseEntity<List<Survey>> read() {
        return surveyService.all();
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<Survey> read(@PathVariable("surveyId") String surveyId) {
        return surveyService.read(surveyId);
    }

    @GetMapping("/score/{score}")
    public ResponseEntity<List<Survey>> searchByScore(@PathVariable("score") Integer score) {
        return surveyService.searchByScoreAndStatus(score, Util.COMPLETED);
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Survey>> searchByScore(@PathVariable("jobId") String jobId) {
        return surveyService.searchByJobIdAndStatus(jobId, Util.COMPLETED);
    }

    @PutMapping()
    public ResponseEntity<Survey> update(@RequestBody Survey survey) {
        return surveyService.update(survey);
    }

    @DeleteMapping("/{surveyId}")
    public ResponseEntity<Void> delete(@PathVariable("surveyId") String surveyId) {
        surveyService.delete(surveyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /** Rest endpoint to create sample json data **/
    @GetMapping("/sample")
    public ResponseEntity<Survey> test() {

        Question q1 = Question.builder()
                .question("what is your favourite colour")
                .answers(Arrays.asList("blue","red","white","yellow"))
                .correctAnswers(Arrays.asList("blue"))
                .userAnswers(Arrays.asList("blue"))
                .points(Map.of("blue",1))
                .build();

        Question q2 = Question.builder()
                .question("What are your favourite programming languages, specify three")
                .answers(Arrays.asList("java","python","react","go","scala"))
                .correctAnswers(Arrays.asList("java","python","react"))
                .userAnswers(Arrays.asList("java","python","react"))
                .points(Map.of("java",1, "python", 1, "react",1, "docker",0))
                .build();

        Question q3 = Question.builder()
                .question("what is your favourite ide")
                .answers(Arrays.asList("intellij", "eclipse","netbeans","sts"))
                .correctAnswers(Arrays.asList("intellij"))
                .userAnswers(Arrays.asList("intellij"))
                .points(Map.of("intellij",2, "eclipse", 1,"netbeans", 1,"sts", 1))
                .build();

        Question q4 = Question.builder()
                .question("Select two statements which match your values")
                .answers(Arrays.asList("Entrepreneurial", "Detail Oriented", "Disruptive", "Survival of the fittest", "By any means necessary"))
                .correctAnswers(Arrays.asList("Disruptive","Entrepreneurial"))
                .userAnswers(Arrays.asList("Disruptive","Entrepreneurial"))
                .points(Map.of("Disruptive",2, "Entrepreneurial", 1))
                .build();


        Survey s1 = Survey.builder()
                .questions(Arrays.asList(q1, q2, q3, q4))
                .jobId("J1")
                .candidateId("CA1")
                .companyId("C01")
                .name("sankar")
                .status(Util.COMPLETED)
                .build();

        s1.setScore(Util.calculateScore(Arrays.asList(q1, q2, q3, q4)));

        return ResponseEntity.status(HttpStatus.OK).body(s1);
    }

}