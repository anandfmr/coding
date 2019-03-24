package com.test.coding.entity;


import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ApplicationCode implements Serializable {

    @NotNull
    private String offer;

    @NotNull
    private String candidateEmail;

    public ApplicationCode() {
    }

    public ApplicationCode(@NotNull String jobTitle, @NotNull String applicantEmailId) {
        this.offer = jobTitle;
        this.candidateEmail = applicantEmailId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationCode that = (ApplicationCode) o;
        return Objects.equals(offer, that.offer) &&
                Objects.equals(candidateEmail, that.candidateEmail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(offer, candidateEmail);
    }
}
