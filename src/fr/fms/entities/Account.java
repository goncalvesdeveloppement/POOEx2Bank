package fr.fms.entities;

import java.util.Date;

import fr.fms.application.BankApp;
import fr.fms.business.BusinessImpl;

public class Account {
	private int accountID;
	private Double balance;
	private Date creationDate;
	private Customer owner;
	private static int instanceCount = 0;

	/* ---------- CONSTRUCTORS ---------- */


	public Account() {
		this(0);
	}

	public Account(double balance) {
		this.setAccountID(this.getInstanceCount());
		setInstanceCount(instanceCount + 1);
		this.setBalance(balance);
		this.setCreationDate(new Date());
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public int getInstanceCount() {
		return instanceCount;
	}

	public void setInstanceCount(int instanceCount_) {
		instanceCount = instanceCount_;
	}

	// Adds/substracts to balance the amount
	public void adjustBalance(double amount) {
		this.setBalance(this.getBalance() + amount);
		 BankApp.business.getTransactionsList().add (new Transaction(0, amount));
	} 
	
	public String toString() {
		return "Account [[accountID = " + this.getAccountID() + "][balance = " + this.getBalance() + "][owner = " + this.getOwner() + "][creationDate = '" + this.getCreationDate() + "']]";
	}
}
