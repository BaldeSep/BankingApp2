package com.bank.to.types;

public enum MoneyTransferStatus {
	Rejected,
	Pending,
	Accepted;
	
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
