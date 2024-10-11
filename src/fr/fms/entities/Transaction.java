package fr.fms.entities;

import java.util.Date;

public class Transaction {
	/* ---------- ATTRIBUTES ---------- */

	private int transactionID;					// L'id de transaction, incrémenté automatiquement
	private Date transactionDate; 				// La date de la transaction
	private double amount;						// Le montant de l'opération (positif/négatif)
	private Account targetAccount;				// Le compte ciblé par la transaction
	private static int instanceCount = 0;		// Le nombre de transactions (pour générer les nouveaux ID)

	/* ---------- CONSTRUCTORS ---------- */
	
	public Transaction(double amount, Account targetAccount) {
		this.setTransactionID(instanceCount);
		this.setAmount(amount);
		this.setTransactionDate(new Date());
		this.setTargetAccount(targetAccount);
		setInstanceCount(getInstanceCount() + 1);
	}
	
	/* ---------- GETTERS/SETTERS ---------- */

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Account getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(Account targetAccount) {
		this.targetAccount = new Account(targetAccount.getBalance());
	}
	
	public static int getInstanceCount() {
		return instanceCount;
	}

	public static void setInstanceCount(int instanceCount) {
		Transaction.instanceCount = instanceCount;
	}

	/* ---------- METHODS ---------- */

	public String toString() {
		return "Transaction [[transactionID = " + this.getTransactionID() + " la transaction a été effectuée le : "+ transactionDate + " le montant est de : " + amount;//
	}
}