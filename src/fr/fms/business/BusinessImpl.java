/**
* 
*/
package fr.fms.business;

import java.util.ArrayList;
import fr.fms.entities.Account;
import fr.fms.entities.CurrentAccount;
import fr.fms.entities.SavingAccount;
import fr.fms.entities.Transaction;
import fr.fms.entities.Customer;

public class BusinessImpl implements Business {
	/* ---------- ATTRIBUTES ---------- */

	private static ArrayList<Account> accountList = new ArrayList<Account>();
	private static ArrayList<Customer> customerList = new ArrayList<Customer>();
	private static ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

	/* ---------- GETTERS/SETTERS ---------- */

	public ArrayList<Account> getAccountList() {
		return accountList;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setAccountList(ArrayList<Account> accountList) {
		BusinessImpl.accountList = accountList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		BusinessImpl.customerList = customerList;
	}

	/* ---------- METHODS ---------- */

	// Réalise un virement (d'un montant positif) d'un compte expéditeur vers un compte destinataire
	public String transfer(int senderAccountID, int destAccountID, double amount) {

		// Si les comptes sont différents et le montant est positif
		if ((senderAccountID != destAccountID) && amount > 0) {

			// Si l'opération de retrait sur le compte expéditeur est possible
			if (operation(senderAccountID, -amount).equalsIgnoreCase("Retrait correctement effectué.")) {
				operation(destAccountID, amount);
				return "Virement effectué.";
			} else
				return "Erreur — fonds insuffisants sur le compte débiteur...";
		} else if (amount <= 0)
			return "Erreur — le montant doit être positif...";
		else
			return "Erreur — les comptes débiteur et destinataire sont identiques...";
	}

	// Réalise une opération élémentaire (retrait ou versement) d'une certaine valeur sur un compte donné
	public String operation(int accountID, double amount) {
		Account account = getAccountByID(accountID);

		// Si le montant est négatif = retrait
		if (amount < 0) {
			amount -= 2 * amount; // Identique à "amount = -amount"

			// Si le solde est supérieur ou égal au montant
			if (account.getBalance() >= amount) {
				account.adjustBalance(-amount);
				createTransaction(account, amount);
				return "Retrait correctement effectué.";

			// Sinon, si c'est un compte courant...
			} else if (account instanceof CurrentAccount) {

				// ...et avec un découvert autorisé
				if (((CurrentAccount) (account)).isAllowedOverdraft()) {
					account.adjustBalance(-amount);
					return "Retrait correctement effectué.";
				} else
					return "Erreur — vous avez dépassé vos capacités de retrait...";
			} else
				return "Erreur — solde insuffisant...";

		// Si le montant est positif = versement
		} else if (amount > 0) {
			account.adjustBalance(amount);
			createTransaction(account, amount);
			return "Dépôt correctement pris en compte.";
		} else {
			return "Erreur — le montant doit être différent de zéro...";
		}
	}

	// Crée une nouvelle transaction
	public String createTransaction(Account account, double amount) {
		Transaction transaction = new Transaction(amount, account);
		transactionList.add(transaction);

		return "Transaction ajoutée.";
	}

	// Récupère un objet compte par son ID
	public Account getAccountByID(int accountID) {
		for (Account account : getAccountList()) {
			if (account.getAccountID() == accountID) {
				return account;
			}
		}

		return null;
	}

	// Récupère un objet client par son ID
	public Customer getCustomerByID(int customerID) {
		for (Customer customer : getCustomerList()) {
			if (customer.getCustomerID() == customerID) {
				return customer;
			}
		}

		return null;
	}

	// Créer un nouveau compte pour un client (spécifié par son ID client) en indiquant s'il s'agit ou non d'un compte épargne
	public String createAccount(int customerID, boolean isSavingAccount) {
		Customer temp = getCustomerByID(customerID);
		if (temp != null) {
			Account newAccount;

			if (isSavingAccount)
				newAccount = new SavingAccount(0, 10);
			else
				newAccount = new CurrentAccount(0, false);

			newAccount.setOwner(temp);
			getAccountList().add(newAccount);
			return "OK";
		} else {
			return "ERR_NO_CUSTOMER";
		}
	}

	// Créer un nouveau client avec son nom, prénom, email
	public String createCustomer(String lastName, String firstName, String email) {
		for (Customer customer : getCustomerList()) {
			if (customer.getEmail().equalsIgnoreCase(email))
				return "Erreur — l'email est déjà utilisé...";
		}

		Customer newCustomer = new Customer(lastName, firstName, email);
		getCustomerList().add(newCustomer);
		return "Client " + lastName + " " + firstName + " ajouté !";
	}

	// Récupère le solde d'un compte
	public double getAccountBalance(int accountID) {
		Account tmpAccount = getAccountByID(accountID);
		return tmpAccount.getBalance();
	}

	// Récupère tous les comptes d'un client (spécifié par son ID client)
	public ArrayList<Account> getCustomerAccounts(int customerID) {
		ArrayList<Account> emptyList = new ArrayList<Account>();
		for (Account account : getAccountList()) {
			if (account.getOwner().getCustomerID() == customerID)
				emptyList.add(account);
		}
		return emptyList;
	}

	// Récupère toutes les transactions d'un compte (spécifié par son ID)
	public ArrayList<Transaction> getAccountTransactions(int accountID) {
		ArrayList<Transaction> emptyList = new ArrayList<Transaction>();
		for (Transaction transaction : getTransactionList()) {
			if (transaction.getTargetAccount().getAccountID() == accountID)
				emptyList.add(transaction);
		}
		return emptyList;
	}

}