/**
* 
*/
package fr.fms.bank.business;

import java.util.ArrayList;

import fr.fms.bank.entities.Account;
import fr.fms.bank.entities.CurrentAccount;
import fr.fms.bank.entities.Customer;
import fr.fms.bank.entities.SavingAccount;
import fr.fms.bank.entities.Transaction;
import fr.fms.exceptions.BadCustomerException;
import fr.fms.exceptions.BadInputAccountException;
import fr.fms.exceptions.BadInputAmountException;
import fr.fms.exceptions.InsufficientFundsException;

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

	// Réalise un virement (d'un montant positif) d'un compte expéditeur vers un
	// compte destinataire
	public void transfer(int senderAccountID, int destAccountID, double amount)
			throws InsufficientFundsException, BadInputAmountException, BadInputAccountException {

		// Si les comptes sont différents et le montant est positif
		if ((senderAccountID != destAccountID) && amount > 0) {

			// Si l'opération de retrait sur le compte expéditeur est possible
			try {
				if (operation(senderAccountID, -amount)) {
					operation(destAccountID, amount);
				} else
					throw new InsufficientFundsException();
			} catch (InsufficientFundsException | BadInputAmountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (amount <= 0)
			throw new BadInputAmountException();
		else
			throw new BadInputAccountException("Le compte destinataire doit être différent du compte expéditeur !");
	}

	// Réalise une opération élémentaire (retrait ou versement) d'une certaine
	// valeur sur un compte donné
	public boolean operation(int accountID, double amount) throws InsufficientFundsException, BadInputAmountException {
		Account account = null;
		try {
			account = getAccountByID(accountID);
		} catch (BadInputAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (account != null) {
			// Si le montant est négatif = retrait
			if (amount < 0) {
				amount -= 2 * amount; // Identique à "amount = -amount"

				// Si le solde est supérieur ou égal au montant
				if (account.getBalance() >= amount) {
					account.adjustBalance(-amount);
					createTransaction(account, -amount);
					return true;

					// Sinon, si c'est un compte courant...
				} else if (account instanceof CurrentAccount) {

					// ...et avec un découvert autorisé
					if (((CurrentAccount) (account)).isAllowedOverdraft()) {
						account.adjustBalance(-amount);
						return true;
					} else
						throw new InsufficientFundsException();
				} else
					throw new InsufficientFundsException();

				// Si le montant est positif = versement
			} else if (amount > 0) {
				account.adjustBalance(amount);
				createTransaction(account, amount);
				return true;
			} else {
				throw new BadInputAmountException();
			}
		}
		else return false;
	}

	// Crée une nouvelle transaction
	public String createTransaction(Account account, double amount) {
		Transaction transaction = new Transaction(amount, account);
		transactionList.add(transaction);

		return "Transaction ajoutée.";
	}

	// Récupère un objet compte par son ID
	public Account getAccountByID(int accountID) throws BadInputAccountException {
		for (Account account : getAccountList()) {
			if (account.getAccountID() == accountID) {
				return account;
			}
		}
		throw new BadInputAccountException("Aucun compte n'existe pour cet ID...");
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

	// Créer un nouveau compte pour un client (spécifié par son ID client) en
	// indiquant s'il s'agit ou non d'un compte épargne
	public String createAccount(int customerID, boolean isSavingAccount) throws BadCustomerException {
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
			throw new BadCustomerException();
		}
	}

	// Créer un nouveau client avec son nom, prénom, email
	public String createCustomer(String lastName, String firstName, String email) throws BadCustomerException {
		for (Customer customer : getCustomerList()) {
			if (customer.getEmail().equalsIgnoreCase(email))
				throw new BadCustomerException("L'e-mail est déjà utilisé !");
		}

		Customer newCustomer = new Customer(lastName, firstName, email);
		getCustomerList().add(newCustomer);
		return "Client " + lastName + " " + firstName + " ajouté !";
	}

	// Récupère le solde d'un compte
	public double getAccountBalance(int accountID) {
		Account tmpAccount;
		try {
			tmpAccount = getAccountByID(accountID);
			return tmpAccount.getBalance();
		} catch (BadInputAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
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
		ArrayList<Transaction> tempList = new ArrayList<Transaction>();
		for (Transaction transaction : getTransactionList()) {
			if (transaction.getTargetAccount().getAccountID() == accountID)
				tempList.add(transaction);
		}
		return tempList;
	}

}