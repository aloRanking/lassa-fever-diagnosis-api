package com.aloranking.lfds.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "survey")
public class Survey  extends  AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long survey_id;

    @NotNull
    @Column(name = "full_name")
    private String full_name;


    @NotNull
    @Column(name = "details")
    private String details;

    public Long getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(Long survey_id) {
        this.survey_id = survey_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
