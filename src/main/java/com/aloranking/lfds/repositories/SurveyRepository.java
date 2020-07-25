package com.aloranking.lfds.repositories;


import com.aloranking.lfds.models.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
