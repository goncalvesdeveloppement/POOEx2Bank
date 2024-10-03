package fr.fms.entities;

public class CurrentAccount extends Account {
	private boolean allowedOverdraft;
	
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
}
