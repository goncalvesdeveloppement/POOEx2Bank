package fr.fms.entities;

public class Customer {
	private int customerID;
	private String lastName, firstName;
	private String email;

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
