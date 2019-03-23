package com.test.coding.entity;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ApplicationCode implements Serializable {

	@NotNull
	private String offer;

	@NotNull
	private String applicantEmailId;

	public ApplicationCode() {
	}

	public ApplicationCode(@NotNull String offer, @NotNull String applicantEmailId) {
		this.offer = offer;
		this.applicantEmailId = applicantEmailId;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getApplicantEmailId() {
		return applicantEmailId;
	}

	public void setApplicantEmailId(String applicantEmailId) {
		this.applicantEmailId = applicantEmailId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ApplicationCode)) {
			return false;
		}
		ApplicationCode that = (ApplicationCode) o;
		return Objects.equals(getOffer(), that.getOffer()) &&
			   Objects.equals(getApplicantEmailId(), that.getApplicantEmailId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getOffer(), getApplicantEmailId());
	}
}
