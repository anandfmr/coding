package com.test.coding.entity;


import com.test.coding.model.ApplicationStatus;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Application {

	@EmbeddedId
	private ApplicationCode code;
	private String resume;

	@Enumerated(EnumType.STRING)
	private ApplicationStatus applicationStatus;

	public Application() {
	}

	public Application(ApplicationCode code, String resume, ApplicationStatus applicationStatus) {
		this.code = code;
		this.resume = resume;
		this.applicationStatus = applicationStatus;
	}

	public ApplicationCode getCode() {
		return code;
	}

	public void setCode(ApplicationCode code) {
		this.code = code;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
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
		if (!(o instanceof Application)) {
			return false;
		}
		Application that = (Application) o;
		return Objects.equals(getCode(), that.getCode()) &&
			   Objects.equals(getResume(), that.getResume()) &&
			   getApplicationStatus() == that.getApplicationStatus();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCode(), getResume(), getApplicationStatus());
	}
}
