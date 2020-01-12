package com.bank.to;

import java.util.Date;

public class BankAccount {
	private int accountNumber;
	private String holder;
	private double balance;
	private Date dateCreated;
	
	public BankAccount() {
		// TODO Auto-generated constructor stub
	}

	public BankAccount(int accountNumber, String holder, double balance, Date dateCreated) {
		super();
		this.accountNumber = accountNumber;
		this.holder = holder;
		this.balance = balance;
		this.dateCreated = dateCreated;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", holder=" + holder + ", balance=" + balance
				+ ", dateCreated=" + dateCreated + "]";
	}
}
