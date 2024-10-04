/**
* 
*/
package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;

import fr.fms.entities.Account;
import fr.fms.entities.CurrentAccount;
import fr.fms.entities.SavingAccount;
import fr.fms.entities.Customer;

public class BusinessImpl implements Business {

	// Pour récupérer la liste des comptes qui existent, on récupère l'ID dans les
	// objets en question
	private static ArrayList<Account> accountList = new ArrayList<Account>();
	private static ArrayList<Customer> customerList = new ArrayList<Customer>();

	// accesseurs arraylist
	public static ArrayList<Account> getAccountList() {
		return accountList;
	}

	public static ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public static void setAccountList(ArrayList<Account> accountList) {
		BusinessImpl.accountList = accountList;
	}

	public static void setCustomerList(ArrayList<Customer> customerList) {
		BusinessImpl.customerList = customerList;
	}

	// Virement (retrait + versement)
	public String Transfer(int senderAccountID, int destAccountID, double amount) {
		if ((senderAccountID != destAccountID)) {
			if (Withdraw(senderAccountID, amount).equalsIgnoreCase("Retrait correctement effectué.")) {
				Deposit(destAccountID, amount);
				return "Virement effectué.";
			} else
				return "Erreur — fonds insuffisants sur le compte débiteur...";
		} else
			return "Erreur — les comptes débiteur et destinataire sont identiques...";
	}

	// Versement
	public String Deposit(int accountID, double amount) {
		Account account = GetAccountByID(accountID);

		if (amount > 0) {
			account.adjustBalance(amount);
			return "Dépôt correctement pris en compte.";
		} else {
			return "Erreur — le montant à déposer doit être positif...";
		}
	}

	// Retrait
	public String Withdraw(int accountID, double amount) {
		Account account = GetAccountByID(accountID);

		if (amount > 0) {
			if (account.getBalance() >= amount) {
				account.adjustBalance(-amount);
				return "Retrait correctement effectué.";
			} else if (account instanceof CurrentAccount) {
				if (((CurrentAccount) (account)).isAllowedOverdraft()) {
					account.adjustBalance(-amount);
					return "Retrait correctement effectué.";
				}
				else
					return "Erreur — vous avez dépassé vos capacités de retrait...";
			}
			else			
				return "Erreur — mauvais type de compte...";
		} else {
			return "Erreur — le montant à retirer doit être positif...";
		}
	}

	// On récupère le compte en fonction de l'ID du customer
	public Account GetAccountByID(int accountID) {
		for (Account account : getAccountList()) {
			if (account.getAccountID() == accountID) {
				return account;
			}
		}

		return null;
	}

	public Customer GetCustomerByID(int customerID) {
		for (Customer customer : getCustomerList()) {
			if (customer.getCustomerID() == customerID) {
				return customer;
			}
		}

		return null;
	}

	@Override
	public String CreateAccount(int customerID, boolean isSavingAccount) {
		Customer temp = GetCustomerByID(customerID);
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

	@Override
	public String CreateCustomer(String lastName, String firstName, String email) {
		for (Customer customer : getCustomerList()) {
			if (customer.getEmail().equalsIgnoreCase(email))
				return "Erreur — l'email est déjà utilisé...";
		}

		Customer newCustomer = new Customer(lastName, firstName, email);
		getCustomerList().add(newCustomer);
		return "Client " + lastName + " " + firstName + " ajouté !";
	}

	@Override
	public double GetAccountBalance(int accountID) {
		Account tmpAccount = GetAccountByID(accountID);
		return tmpAccount.getBalance();
	}

	@Override
	public ArrayList<Account> GetCustomerAccounts(int customerID) {
		ArrayList<Account> emptyList = new ArrayList<Account>();
		for (Account account : getAccountList()) {
			if (account.getOwner().getCustomerID() == customerID)
				emptyList.add(account);
		}
		return emptyList;
	}
}
