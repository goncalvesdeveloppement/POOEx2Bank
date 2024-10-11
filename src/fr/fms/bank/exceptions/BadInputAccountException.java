package fr.fms.bank.exceptions;

public class BadInputAccountException extends Exception {
	public BadInputAccountException(String msg) {
		super(msg);
	}
	
	public BadInputAccountException() {
		this("Le compte saisi est invalide !");
	}
}
