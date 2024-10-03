package fr.fms.entities;

public class SavingAccount extends Account {
	private double interestRate;

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
}
