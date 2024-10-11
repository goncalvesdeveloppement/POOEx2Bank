package fr.fms.bank.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import fr.fms.bank.business.BusinessImpl;
import fr.fms.bank.entities.Account;
import fr.fms.bank.entities.CurrentAccount;
import fr.fms.bank.entities.Transaction;
import fr.fms.bank.exceptions.BadCustomerException;
import fr.fms.bank.exceptions.BadInputAccountException;
import fr.fms.bank.exceptions.BadInputAmountException;
import fr.fms.bank.exceptions.InsufficientFundsException;

public class BankApp {
	/* ---------- ATTRIBUTS ---------- */

	public static BusinessImpl business = new BusinessImpl(); // Implémentation de l'interface métier.
	public static Scanner scan = new Scanner(System.in); // Gestion des saisies utilisateurs.

	/* ---------- MÉTHODES ---------- */

	// Affiche le menu principal et demande un choix à l'utilisateur.
	public static int MainMenu() {
		/* ---------- AFFICHAGE ---------- */
		System.out.println("************ Entrez le chiffre correspondant à votre choix : ************");
		System.out.printf("%-20s", "[1] Versement");
		System.out.printf("%-20s", "[2] Retrait");
		System.out.printf("%-20s", "[3] Virement");
		System.out.printf("%-20s", "[4] Info solde");
		System.out.printf("%-20s", "[5] Historique");
		System.out.printf("%-20s", "[6] Sortir");

		/* ---------- GESTION SAISIE ---------- */
		int result = 0;

		while (result < 1 || result > 6) {
			result = ReadInt();
		}

		return result;
	}

	// Initialise des variables et instances fictives pour tests.
	public static void init() throws BadCustomerException {
		business.createCustomer("albert", "marie", "bjr@fms-ea.com");
		business.createCustomer("fred", "jean", "bjr@fms2.com");
		business.createAccount(0, false);
		business.createAccount(0, false);
		business.createAccount(1, true);
		business.createAccount(1, true);
		business.createAccount(1, true);
	}

	// Invite l'utilisateur à saisir un entier
	public static int ReadInt() {
		while (!scan.hasNextInt())
			scan.next();

		return scan.nextInt();
	}

	public static void main(String[] args) {

		/* ---------- INITIALISATION ---------- */
		try {
			init();
		} catch (BadCustomerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		/* ---------- SAISIE NUMÉRO DE COMPTE ---------- */
		int accountID = -1;
		boolean validAccount = false;

		System.out.println("Bonjour, entrez un numéro de compte valide : ");

		while (!validAccount) {
			accountID = ReadInt();
			try {
				if (business.getAccountByID(accountID) != null)
					validAccount = true;
				else
					System.out.println("Numéro de compte invalide...");
			} catch (BadInputAccountException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}

		Account account = null;
		try {
			account = business.getAccountByID(accountID);
		} catch (BadInputAccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		if (account != null) {
			/* ---------- MENU PRINCIPAL ---------- */
			int menuChoice = 0;

			System.out.println("Bienvenue, " + account.getOwner().getFirstName() + " "
					+ account.getOwner().getLastName().toUpperCase() + ".");

			while (menuChoice != 6) {
				menuChoice = MainMenu();

				switch (menuChoice) {
				case 1:
					System.out.println("Quel montant déposer ?");
					int amountToDeposit = ReadInt();
					try {
						System.out.println(business.operation(accountID, amountToDeposit));
					} catch (InsufficientFundsException | BadInputAmountException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 2:
					System.out.println("Quel montant retirer (" + String.format("%.0f", account.getBalance())
							+ " € disponibles) ?");
					int amountToWithdraw = ReadInt();
					try {
						System.out.println(business.operation(accountID, -amountToWithdraw));
					} catch (InsufficientFundsException | BadInputAmountException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					System.out.println("Quel montant transférer (" + String.format("%.2f", account.getBalance())
							+ " € disponibles) ?");
					int amountToTransfer = ReadInt();
					System.out.println("Entrez le numéro de compte du bénéficiaire.");
					int destAccountID = ReadInt();
					try {
						business.transfer(accountID, destAccountID, amountToTransfer);
					} catch (InsufficientFundsException | BadInputAmountException | BadInputAccountException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 4:
					System.out.println("Le solde de votre compte "
							+ (account instanceof CurrentAccount ? "courant" : "épargne") + " n° " + accountID
							+ " est de " + String.format("%.2f", account.getBalance()) + " €.");
					break;
				case 5:
					System.out.println("Voici l'historique des transactions liées à votre compte n°" + accountID + ".");
					ArrayList<Transaction> transactions = business.getAccountTransactions(accountID);
					Collections.reverse(transactions);
					transactions.forEach((t) -> System.out.println(t));
					break;
				default:
					break;
				}
			}

			System.out.println("Bye bye...");
		}
	}
}