package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Account;
import fr.fms.entities.Customer;

public interface Business {
	public String CreateAccount(int customerID, boolean isSavingAccount);
	public String CreateCustomer(String lastName, String firstName, String email);
	public String Deposit(int accountID, double amount);
	public double GetAccountBalance(int accountID);
	public ArrayList<Account> GetCustomerAccounts(int customerID);
	public Account GetAccountByID(int accountID);
	public String Transfer(int senderAccountID, int destAccountID, double amount);
	public String Withdraw(int accountID, double amount);
	public Customer GetCustomerByID(int customerID);
}