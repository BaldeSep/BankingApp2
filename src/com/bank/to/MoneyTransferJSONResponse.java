package com.bank.to;

public class MoneyTransferJSONResponse {
	private int transferId;
	private int sourceAccount;
	private String sourceHolder;
	private int destinationAccount;
	private String destinationHolder;
	private double amount;
	private int status;
	public MoneyTransferJSONResponse() {
		// TODO Auto-generated constructor stub
	}
	public MoneyTransferJSONResponse(int transferId, int sourceAccount, String sourceHolder, int destinationAccount,
			String destinationHolder, double amount, int status) {
		super();
		this.transferId = transferId;
		this.sourceAccount = sourceAccount;
		this.sourceHolder = sourceHolder;
		this.destinationAccount = destinationAccount;
		this.destinationHolder = destinationHolder;
		this.amount = amount;
		this.status = status;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public int getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(int sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public String getSourceHolder() {
		return sourceHolder;
	}
	public void setSourceHolder(String sourceHolder) {
		this.sourceHolder = sourceHolder;
	}
	public int getDestinationAccount() {
		return destinationAccount;
	}
	public void setDestinationAccount(int destinationAccount) {
		this.destinationAccount = destinationAccount;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "MoneyTransferJSONResponse [transferId=" + transferId + ", sourceAccount=" + sourceAccount
				+ ", sourceHolder=" + sourceHolder + ", destinationAccount=" + destinationAccount
				+ ", destinationHolder=" + destinationHolder + ", amount=" + amount + ", status=" + status + "]";
	}
	
}
