package com.serand.assignment.controller;


import com.serand.assignment.model.Candidate;
import com.serand.assignment.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Responsible for all user related endpoints.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidates")
public class CandidateController {
	@Autowired
	private CandidateRepository candidateRepository;

	// ... existing endpoints

	@PostMapping
	public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
		Candidate savedCandidate = candidateRepository.save(candidate);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCandidate);
	}

}