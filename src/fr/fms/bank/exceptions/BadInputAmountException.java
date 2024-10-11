package fr.fms.bank.exceptions;

public class BadInputAmountException extends Exception {
	public BadInputAmountException(String msg) {
		super(msg);
	}
	
	public BadInputAmountException() {
		this("Le montant saisi doit être positif !");
	}
}
