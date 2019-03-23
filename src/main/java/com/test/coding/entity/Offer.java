package com.test.coding.entity;


import com.test.coding.model.OfferRequest;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Formula;

@Entity
public class Offer {

	@Id
	private String jobTitle;
	private LocalDate startDate;

	@Formula("select count(*) from application a where a.offer=job_title)")
	private int numberOfApplication;

	protected Offer(){ }

	public Offer(String jobTitle, LocalDate startDate) {
		this.jobTitle = jobTitle;
		this.startDate = startDate;
	}

	public Offer(OfferRequest request){
		this(
				request.getJobTitle(),
				request.getStartDate()
		);
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public int getNumberOfApplication() {
		return numberOfApplication;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Offer)) {
			return false;
		}
		Offer offer = (Offer) o;
		return Objects.equals(getJobTitle(), offer.getJobTitle()) &&
			   Objects.equals(getStartDate(), offer.getStartDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getJobTitle(), getStartDate());
	}
}
