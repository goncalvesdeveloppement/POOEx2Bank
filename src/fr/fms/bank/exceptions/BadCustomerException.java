package fr.fms.bank.exceptions;

public class BadCustomerException extends Exception {
	public BadCustomerException(String msg) {
		super(msg);
	}
	
	public BadCustomerException() {
		this("Le client n'existe pas...");
	}
}