package com.bank.to.types;

public enum MoneyTransferStatus {
	Pending(1),
	Accepted(2),
	Rejected(0);
	
	public final int VALUE;
	
	private MoneyTransferStatus(int value) {
		this.VALUE = value;
	}
	
	public static MoneyTransferStatus fromInt(int value) {
		switch(value) {
		case 0:
			return Rejected;
		case 1:
			return Pending;
		case 2:
			return Accepted;
		default: 
			return null;
		}
	}
}
