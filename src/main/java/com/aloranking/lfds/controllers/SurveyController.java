package com.aloranking.lfds.controllers;

import com.aloranking.lfds.models.Survey;
import com.aloranking.lfds.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/survey")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey){

      return  surveyRepository.saveAndFlush(survey);
    }



}
