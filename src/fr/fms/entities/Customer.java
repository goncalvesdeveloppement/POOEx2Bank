package fr.fms.entities;

public class Customer {
	private int customerID;
	
	/* ---------- CONSTRUCTORS ---------- */

	public Customer(int customerID) {
		this.customerID = customerID;
	}

	/* ---------- GETTERS/SETTERS ---------- */
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}	
}
