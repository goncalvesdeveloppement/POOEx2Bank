package fr.fms.entities;

public class Transaction {
	private int transactionID;
	private String transactionDate; // "AAAA-MM-JJ";
	private double amount;
	
	private Account targetAccount;
	private static final int UNKNOWN_TRANSACTION_ID = 0;

	/* ---------- CONSTRUCTORS ---------- */
	
	public Transaction() {
		this(UNKNOWN_TRANSACTION_ID);
	}
	
	public Transaction(int transactionID) {
		this(transactionID, 0);
	}
	
	public Transaction(int transactionID, double amount) {
		this.setTransactionID(transactionID);
		this.setAmount(amount);
		this.setTransactionDate("2024-10-02");
	}
	
	/* ---------- GETTERS/SETTERS ---------- */

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
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
		this.targetAccount = new Account(targetAccount.getAccountID(), targetAccount.getBalance());
	}
}
