package fr.fms.entities;

public class Administrator {
	private int adminID;
	
	/* ---------- CONSTRUCTORS ---------- */

	public Administrator(int adminID) {
		this.adminID = adminID;
	}

	/* ---------- GETTERS/SETTERS ---------- */
	
	public int getAdminID() {
		return adminID;
	}

	public void setCustomerID(int adminID) {
		this.adminID = adminID;
	}
}
