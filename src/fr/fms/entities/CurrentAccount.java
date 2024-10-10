package fr.fms.entities;

public class CurrentAccount extends Account {
	/* ---------- ATTRIBUTES ---------- */

	private boolean allowedOverdraft;			// Indique si un découvert est autorisé ou non
	
	/* ---------- CONSTRUCTORS ---------- */
	
	public CurrentAccount(double balance, boolean allowedOverdraft) {
		super(balance);
		this.setAllowedOverdraft(allowedOverdraft);
	}
	
	/* ---------- GETTERS/SETTERS ---------- */

	public boolean isAllowedOverdraft() {
		return allowedOverdraft;
	}

	public void setAllowedOverdraft(boolean allowedOverdraft) {
		this.allowedOverdraft = allowedOverdraft;
	}
	
	/* ---------- METHODS ---------- */
	
	public String toString() {
		return "Account [[accountID = " + this.getAccountID() + "][balance = " + this.getBalance() + "][owner = " + this.getOwner() + "][creationDate = '" + this.getCreationDate() + "'][isAllowedDraft = " + this.isAllowedOverdraft() + "]]";
	}
}
