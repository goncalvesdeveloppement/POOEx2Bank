package fr.fms.bank.entities;

public class Customer {
	/* ---------- ATTRIBUTES ---------- */

	private int customerID;						// L'id du client, incrémenté automatiquement
	private String lastName, firstName;			// Les nom et prénom du client
	private String email;						// L'adresse e-mail du client, qui doit être unique
	private static int instanceCount = 0;		// Le nombre de clients existants (pour générer les nouveaux ID)

	/* ---------- CONSTRUCTORS ---------- */

	public Customer(String lastName, String firstName, String email) {
		this.setCustomerID(this.getInstanceCount());
		setInstanceCount(instanceCount + 1);
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setEmail(email);
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

	/* ---------- METHODS ---------- */
	
	public String toString() {
		return "Customer [[customerID = " + this.getCustomerID() + "][lastName = '" + this.getLastName() + "'][firstName = '" + this.getFirstName() + "'][email = '" + this.getEmail() + "']]";
	}
}