package com.test.coding.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

@JsonAutoDetect
public class OfferRequest {


    private String jobTitle;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    private int noOfApplications;

    public OfferRequest() {
    }

    public OfferRequest(String jobTitle, LocalDate startDate) {
        this.jobTitle = jobTitle;
        this.startDate = startDate;
    }

    public OfferRequest(String jobTitle, LocalDate startDate, int noOfApplications) {
        this.jobTitle = jobTitle;
        this.startDate = startDate;
        this.noOfApplications = noOfApplications;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getNoOfApplications() {
        return noOfApplications;
    }

    public void setNoOfApplications(int noOfApplications) {
        this.noOfApplications = noOfApplications;
    }
}
