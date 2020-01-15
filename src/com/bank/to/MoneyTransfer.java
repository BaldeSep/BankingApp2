package com.bank.to;

import com.bank.to.types.MoneyTransferStatus;

public class MoneyTransfer {
	private int transferId;
	private int sourceAccount;
	private String sourceUserName;
	private int destinationAccount;
	private String destinationUserName;
	private double amount;
	private MoneyTransferStatus status;
	public MoneyTransfer() {}
	public MoneyTransfer(int transferId, int sourceAccount, String sourceUserName, int destinationAccount,
			String destinationUserName, double amount, MoneyTransferStatus status) {
		super();
		this.transferId = transferId;
		this.sourceAccount = sourceAccount;
		this.sourceUserName = sourceUserName;
		this.destinationAccount = destinationAccount;
		this.destinationUserName = destinationUserName;
		this.amount = amount;
		this.status = status;
	}
	
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public MoneyTransferStatus getStatus() {
		return status;
	}
	public void setStatus(MoneyTransferStatus status) {
		this.status = status;
	}
	public int getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(int sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public String getSourceUserName() {
		return sourceUserName;
	}
	public void setSourceUserName(String sourceUserName) {
		this.sourceUserName = sourceUserName;
	}
	public int getDestinationAccount() {
		return destinationAccount;
	}
	public void setDestinationAccount(int destinationAccount) {
		this.destinationAccount = destinationAccount;
	}
	public String getDestinationUserName() {
		return destinationUserName;
	}
	public void setDestinationUserName(String destinationUserName) {
		this.destinationUserName = destinationUserName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "MoneyTransfer [transferId=" + transferId + ", sourceAccount=" + sourceAccount + ", sourceUserName="
				+ sourceUserName + ", destinationAccount=" + destinationAccount + ", destinationUserName="
				+ destinationUserName + ", amount=" + amount + ", status=" + status + "]";
	}

}
