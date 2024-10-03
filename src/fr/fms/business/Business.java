package fr.fms.business;

import fr.fms.entities.Account;

public interface Business {
	public void CreateAccount(int customerID, boolean isSavingAccount);
	public void CreateCustomer(String lastName, String firstName, String email);
	public void Deposit(int accountID, double amount);
	public void DisplayAccountBalance(int accountID);
	public void DisplayAllAccounts();
	public void DisplayCustomerAccounts(int customerID);
	public Account GetAccountByID(int accountID);
	public void Transfer(int senderAccountID, int destAccountID, double amount);
	public boolean Withdraw(int accountID, double amount);
}