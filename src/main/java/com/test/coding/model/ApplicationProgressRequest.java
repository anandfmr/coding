package com.test.coding.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class ApplicationProgressRequest {
    private String offer;
    private String candidateEmail;
    private ApplicationStatus applicationStatus;

    public ApplicationProgressRequest() {
    }

    public ApplicationProgressRequest(String offer, String candidateEmail, ApplicationStatus applicationStatus) {
        this.offer = offer;
        this.candidateEmail = candidateEmail;
        this.applicationStatus = applicationStatus;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
