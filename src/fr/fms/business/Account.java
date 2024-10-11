package fr.fms.entities;
import java.util.Date;

public class Account {
	/* ---------- ATTRIBUTES ---------- */
	
	private int accountID;					// L'ID du compte, incrémenté automatiquement
	private Double balance;					// Le solde du compte
	private Date creationDate;				// La date de création
	private Customer owner;					// Le client titulaire du compte
	private static int instanceCount = 0;	// Le nombre de comptes existants (pour générer les nouveaux ID)

	/* ---------- CONSTRUCTORS ---------- */

	public Account() {
		this(0);
	}

	public Account(double balance) {
		this.setAccountID(this.getInstanceCount());
		setInstanceCount(instanceCount + 1);
		this.setBalance(balance);
		this.setCreationDate(new Date());
	}

	/* ---------- GETTERS/SETTERS ---------- */

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public int getInstanceCount() {
		return instanceCount;
	}

	public void setInstanceCount(int instanceCount_) {
		instanceCount = instanceCount_;
	}

	/* ---------- METHODS ---------- */

	// Ajuste le solde via un certain montant (positif/négatif)
	public void adjustBalance(double amount) {
		this.setBalance(this.getBalance() + amount);
	} 
	
	// Affiche correctement un objet Compte
	public String toString() {
		return "Account [[accountID = " + this.getAccountID() + "][balance = " + this.getBalance() + "][owner = " + this.getOwner() + "][creationDate = '" + this.getCreationDate() + "']]";
	}
}