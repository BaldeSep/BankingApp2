package com.bank.to;

public class MoneyTransferJSONRequest {
	private int transferId;
	private int status;
	public MoneyTransferJSONRequest() {
		// TODO Auto-generated constructor stub
	}
	public MoneyTransferJSONRequest(int transferId, int status) {
		super();
		this.transferId = transferId;
		this.status = status;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "MoneyTransferJSONRequest [transferId=" + transferId + ", status=" + status + "]";
	}
	
}
