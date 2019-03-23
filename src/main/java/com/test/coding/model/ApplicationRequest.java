package com.test.coding.model;



import static com.test.coding.model.ApplicationStatus.*;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.test.coding.entity.Application;
import java.util.Objects;

@JsonAutoDetect
public class ApplicationRequest {

	private String offer;
	private String candidateEmail;
	private String rseume;
	private ApplicationStatus applicationStatus= APPLIED;


	public ApplicationRequest() {
	}

	public ApplicationRequest(String offer, String candidateEmail, String rseume, ApplicationStatus applicationStatus) {
		this.offer = offer;
		this.candidateEmail = candidateEmail;
		this.rseume = rseume;
		this.applicationStatus = applicationStatus;
	}

	public ApplicationRequest(Application application) {
		this(
				application.getCode().getOffer(),
				application.getCode().getApplicantEmailId(),
				application.getResume(),
				application.getApplicationStatus()
		);
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

	public String getRseume() {
		return rseume;
	}

	public void setRseume(String rseume) {
		this.rseume = rseume;
	}

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ApplicationRequest)) {
			return false;
		}
		ApplicationRequest that = (ApplicationRequest) o;
		return Objects.equals(getOffer(), that.getOffer()) &&
			   Objects.equals(getCandidateEmail(), that.getCandidateEmail()) &&
			   Objects.equals(getRseume(), that.getRseume()) &&
			   getApplicationStatus() == that.getApplicationStatus();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getOffer(), getCandidateEmail(), getRseume(), getApplicationStatus());
	}
}
