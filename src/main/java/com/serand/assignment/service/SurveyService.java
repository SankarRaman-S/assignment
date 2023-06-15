package com.serand.assignment.service;


import com.serand.assignment.model.Survey;
import com.serand.assignment.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    public List<Survey> create(Survey survey) {

        Iterable<Survey> iterable = Arrays.asList(survey);
        return surveyRepository.saveAll(iterable);
    }

    public ResponseEntity<List<Survey>> all() {

        List<Survey> all = surveyRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(all);

    }

    public ResponseEntity<Survey> read(String surveyId) {
        Optional<Survey> surveyById = surveyRepository.findById(surveyId);
        if(surveyById.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(surveyById.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<Survey> update(Survey survey) {
        Survey updatedSurvey = surveyRepository.save(survey);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSurvey);
    }

    public void delete(String surveyId) {
        surveyRepository.deleteById(surveyId);
    }


    public ResponseEntity<List<Survey>> searchByScoreAndStatus(Integer score,String status) {
        return ResponseEntity.status(HttpStatus.OK).body(surveyRepository.findByScoreAndStatus(score,"completed"));
    }

    public ResponseEntity<List<Survey>> searchByJobIdAndStatus(String jobId, String status) {
        return ResponseEntity.status(HttpStatus.OK).body(surveyRepository.findByJobIdAndStatus(jobId, status));
    }
}
