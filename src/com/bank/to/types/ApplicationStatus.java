package com.bank.to.types;

public enum ApplicationStatus {
	Denied,
	Pending,
	Accepted;
	
	public static ApplicationStatus fromInt(int value) {
		switch (value) {
		case 0:
			return Denied;
		case 1:
			return Pending;
		case 2:
			return Accepted;
		}
		return null;
	}
}
