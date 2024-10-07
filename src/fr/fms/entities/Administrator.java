package fr.fms.entities;

public class Administrator {
	/* ---------- ATTRIBUTES ---------- */

	private int adminID;
	
	/* ---------- CONSTRUCTORS ---------- */

	public Administrator(int adminID) {
		this.adminID = adminID;
	}

	/* ---------- GETTERS/SETTERS ---------- */
	
	public int getAdminID() {
		return adminID;
	}
	
	/* ---------- METHODS ---------- */

	public void setCustomerID(int adminID) {
		this.adminID = adminID;
	}
}
