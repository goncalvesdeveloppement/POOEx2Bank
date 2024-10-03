package fr.fms.entities;

public class Account {
	private int accountID;
	private Double balance;

	/* ---------- CONSTRUCTORS ---------- */
	
	public Account() {
		this(0);
	}
	
	public Account(double balance) {
		this(0, balance);
	}
	
	public Account(int accountID, double balance) {
		this.setAccountID(accountID);
		this.setBalance(balance);
	}
	
	/* ---------- GETTERS/SETTERS ---------- */

	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	// Adds/substracts to balance the amount
	public Transaction adjustBalance(double amount) {
		this.setBalance(this.getBalance() + amount);
		return new Transaction(0, amount);
	}
}
