package fr.fms.bank.exceptions;

public class InsufficientFundsException extends Exception {
	public InsufficientFundsException(String msg) {
		super(msg);
	}
	
	public InsufficientFundsException() {
		this("Fonds insuffisants sur le compte.");
	}
}