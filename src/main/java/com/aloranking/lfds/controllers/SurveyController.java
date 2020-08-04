package com.aloranking.lfds.controllers;

import com.aloranking.lfds.models.Survey;
import com.aloranking.lfds.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lfds")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @PostMapping("/survey")
    public ResponseEntity<?> createSurvey(@RequestBody Survey survey){

        surveyRepository.saveAndFlush(survey);

        return ResponseEntity.ok("Survey Submitted Successfully");
    }

    @GetMapping
    public List<Survey> getAllSurveys(){
      return  surveyRepository.findAll();
 }



}
