package com.bank.to;

import java.util.Date;

public class TransferLog {
	private int transactionId;
	private int sourceAccount;
	private int destinationAccount;
	private String sourceHolder;
	private String destinationHolder;
	private double amount;
	private Date dateOfPosting;
	public TransferLog() {
	}
	public TransferLog(int transactionId, int sourceAccount, int destinationAccount, String sourceHolder,
			String destinationHolder, double amount, Date dateOfPosting) {
		super();
		this.transactionId = transactionId;
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.sourceHolder = sourceHolder;
		this.destinationHolder = destinationHolder;
		this.amount = amount;
		this.dateOfPosting = dateOfPosting;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(int sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public int getDestinationAccount() {
		return destinationAccount;
	}
	public void setDestinationAccount(int destinationAccount) {
		this.destinationAccount = destinationAccount;
	}
	public String getSourceHolder() {
		return sourceHolder;
	}
	public void setSourceHolder(String sourceHolder) {
		this.sourceHolder = sourceHolder;
	}
	public String getDestinationHolder() {
		return destinationHolder;
	}
	public void setDestinationHolder(String destinationHolder) {
		this.destinationHolder = destinationHolder;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDateOfPosting() {
		return dateOfPosting;
	}
	public void setDateOfPosting(Date dateOfPosting) {
		this.dateOfPosting = dateOfPosting;
	}
	@Override
	public String toString() {
		return "TransferLog [transactionId=" + transactionId + ", sourceAccount=" + sourceAccount
				+ ", destinationAccount=" + destinationAccount + ", sourceHolder=" + sourceHolder
				+ ", destinationHolder=" + destinationHolder + ", amount=" + amount + ", dateOfPosting=" + dateOfPosting
				+ "]";
	}
}
