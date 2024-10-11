package fr.fms.bank.entities;

public class SavingAccount extends Account {
	/* ---------- ATTRIBUTES ---------- */

	private double interestRate; // Taux d'intérêt exprimé en pourcentage relatif

	/* ---------- CONSTRUCTORS ---------- */

	public SavingAccount(double balance, double interestRate) {
		super(balance);
		this.setInterestRate(interestRate);
	}

	/* ---------- GETTERS/SETTERS ---------- */

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		if (interestRate > 0 && interestRate < 100)
			this.interestRate = interestRate;
	}

	/* ---------- METHODS ---------- */
	
	public String toString() {
		return "Account [[accountID = " + this.getAccountID() + "][balance = " + this.getBalance() + "][owner = " + this.getOwner() + "][creationDate = '" + this.getCreationDate() + "'][interestRate = " + interestRate + "]]";
	}
}
