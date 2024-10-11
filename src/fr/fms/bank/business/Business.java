package fr.fms.bank.business;

import java.util.ArrayList;

import fr.fms.bank.entities.Account;
import fr.fms.bank.entities.Customer;
import fr.fms.bank.entities.Transaction;
import fr.fms.exceptions.BadCustomerException;
import fr.fms.exceptions.BadInputAccountException;
import fr.fms.exceptions.BadInputAmountException;
import fr.fms.exceptions.InsufficientFundsException;

public interface Business {
	// Créer un nouveau compte pour un client (spécifié par son ID client) en indiquant s'il s'agit ou non d'un compte épargne
	public String createAccount(int customerID, boolean isSavingAccount) throws BadCustomerException;

	// Créer un nouveau client avec son nom, prénom, email
	public String createCustomer(String lastName, String firstName, String email) throws BadCustomerException;

	// Récupère le solde d'un compte
	public double getAccountBalance(int accountID);

	// Récupère tous les comptes d'un client (spécifié par son ID client)
	public ArrayList<Account> getCustomerAccounts(int customerID);

	// Récupère un objet compte par son ID
	public Account getAccountByID(int accountID) throws BadInputAccountException;

	// Réalise un virement (d'un montant positif) d'un compte expéditeur vers un compte destinataire
	public void transfer(int senderAccountID, int destAccountID, double amount) throws InsufficientFundsException, BadInputAmountException, BadInputAccountException;
	
	// Réalise une opération élémentaire (retrait ou versement) d'une certaine valeur sur un compte donné
	public boolean operation(int accountID, double amount) throws InsufficientFundsException, BadInputAmountException;

	// Récupère un objet client par son ID
	public Customer getCustomerByID(int customerID);

	// Récupère toutes les transactions d'un compte (spécifié par son ID)
	public ArrayList<Transaction> getAccountTransactions(int accountID);

	// Crée une nouvelle transaction
	public String createTransaction(Account account, double amount);
}