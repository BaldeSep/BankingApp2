package com.bank.to;

public class Transaction {
	private int accountNumber;
	private double amount;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(int accountNumber, double amount) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [accountNumber=" + accountNumber + ", amount=" + amount + "]";
	}
	
}
