package com.bank.to;

import java.util.Date;

import com.bank.to.types.TransactionType;

public class TransactionLog {
	private int transactionId;
	private int accountNumber;
	private String accountHolder;
	private double amount;
	private Date date;
	private int transactionType;
	public TransactionLog() {
	}
	public TransactionLog(int transactionId, int accountNumber, String accountHolder, double amount, Date date,
			TransactionType transactionType) {
		super();
		this.transactionId = transactionId;
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.amount = amount;
		this.date = date;
		this.transactionType = transactionType.ordinal();
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	
	public void setTransactionType(TransactionType type) {
		this.transactionType = type.ordinal();
	}
	@Override
	public String toString() {
		return "TransactionLog [transactionId=" + transactionId + ", accountNumber=" + accountNumber
				+ ", accountHolder=" + accountHolder + ", amount=" + amount + ", date=" + date + ", transactionType="
				+ transactionType + "]";
	}	
}
