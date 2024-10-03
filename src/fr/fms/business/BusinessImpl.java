/**
* 
*/
package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;

import fr.fms.entities.Account;
import fr.fms.entities.CurrentAccount;
import fr.fms.entities.Customer;

public class BusinessImpl implements Business {

	// Pour récupérer la liste des comptes qui existent, on récupère l'ID dans les
	// objets en question
	private static ArrayList<Account> accountList = new ArrayList<Account>();
	private static ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	//accesseurs arraylist
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
	public void Transfer(int senderAccountID, int destAccountID, double amount) {
		if (Withdraw(senderAccountID, amount)|| (senderAccountID != destAccountID)) {
			Deposit(destAccountID, amount);
		} else
			System.out.println("Le virement ne peux être effectué. Fonds insuffisants");
	}

	// Versement
	public void Deposit(int accountID, double amount) {
		Account account = GetAccountByID(accountID);

		if (amount > 0) {
			account.adjustBalance(amount);
		} else {
			System.out.println("Le montant doit être positif.");
		}
	}

	// Retrait
	public boolean Withdraw(int accountID, double amount) {
		Account account = GetAccountByID(accountID);
		boolean canWithdraw = false;

		if (amount > 0) {
			if (account.getBalance() >= amount)
				canWithdraw = true;
			else if (account instanceof CurrentAccount) {
				if (((CurrentAccount) (account)).isAllowedOverdraft()) {
					canWithdraw = true;
				} else
					System.out.println("Fonds insuffisants sur le compte.");
			}

			if (canWithdraw)
				account.adjustBalance(-amount);
		} else {
			System.out.println("Le montant doit être positif.");
		}

		return canWithdraw;
	}

	// On récupère le compte en fonction de l'ID du customer
	public Account GetAccountByID(int accountID) {
		for (Account account : accountList) {
			if (account.getAccountID() == accountID) {
				return account;
			}
		}

		throw new RuntimeException("Aucun compte pour cet ID");
	}
	

	@Override
	public void CreateAccount(int customerID, boolean isSavingAccount) {
		

	}

	@Override
	public void CreateCustomer(String lastName, String firstName, String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public double GetAccountBalance(int accountID) {
		Account tmpAccount = GetAccountByID(accountID);
		return tmpAccount.getBalance();

	}

	@Override
	public ArrayList<Account> GetAllAccounts() {
		return getAccountList();

	}

	@Override
	public ArrayList<Account> GetCustomerAccounts(int customerID) {
		ArrayList <Account> emptyList = new ArrayList<Account>();
		for (Account account : accountList) {
			if (account.getOwner().getCustomerID()  == customerID) 
				emptyList.add(account);
			}
		return emptyList;
	}
}
