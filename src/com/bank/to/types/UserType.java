package com.bank.to.types;

public enum UserType {
	Customer,
	Employee;
	
	public static UserType fromInt(int value) {
		switch(value) {
		case 0:
			return Customer;
		case 1:
			return Employee;
		}
		return null;
	}
}
