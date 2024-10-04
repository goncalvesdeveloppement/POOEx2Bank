package fr.fms.entities;

public class Customer {
	private int customerID;
	private String lastName, firstName;
	private String email;
	private static int instanceCount = 0;

	/* ---------- CONSTRUCTORS ---------- */

	public Customer(String lastName, String firstName, String email) {
		setInstanceCount(instanceCount + 1);
		this.setCustomerID(this.getInstanceCount());
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setEmail(email);
		this.setCustomerID(0); // Changer en fonction du nb d'instances
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

	public int getInstanceCount() {
		return instanceCount;
	}

	public void setInstanceCount(int instanceCount_) {
		instanceCount = instanceCount_;
	}
	
	public String toString() {
		return "Customer [[customerID = " + this.getCustomerID() + "][lastName = '" + this.getLastName() + "'][firstName = '" + this.getFirstName() + "'][email = '" + this.getEmail() + "']]";
	}
}