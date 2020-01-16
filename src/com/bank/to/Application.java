package com.bank.to;

import java.time.LocalDateTime;

import com.bank.to.types.ApplicationStatus;

public class Application {
	private int applicationId;
	private double initialBalance;
	private LocalDateTime dateApplied;
	private String applicant;
	private ApplicationStatus status;
	public Application() {
		// TODO Auto-generated constructor stub
	}
	public Application(int applicationId, double initialBalance, LocalDateTime dateApplied, String applicant,
			ApplicationStatus status) {
		super();
		this.applicationId = applicationId;
		this.initialBalance = initialBalance;
		this.dateApplied = dateApplied;
		this.applicant = applicant;
		this.status = status;
	}

	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public double getInitialBalance() {
		return initialBalance;
	}
	public void setInitialBalance(double initialBalance) {
		this.initialBalance = initialBalance;
	}
	public LocalDateTime getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(LocalDateTime dateApplied) {
		this.dateApplied = dateApplied;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", initialBalance=" + initialBalance + ", dateApplied="
				+ dateApplied.toLocalDate() + ", applicant=" + applicant + ", status=" + status + "]";
	}
	
}
