package fr.fms.application;

import fr.fms.business.Business;
import fr.fms.business.BusinessImpl;
import fr.fms.entities.Account;
import fr.fms.entities.CurrentAccount;
import fr.fms.entities.Transaction;

public class BankApp {
	/* ---------- ATTRIBUTS  ---------- */
	
	public static BusinessImpl business = new BusinessImpl();		// Implémentation de l'interface métier.
	public static Scanner scan = new Scanner(System.in);			// Gestion des saisies utilisateurs.
	
	/* ---------- MÉTHODES  ---------- */
	
	// Affiche le menu principal et demande un choix à l'utilisateur.
	public static int MainMenu() {
		/* ---------- AFFICHAGE  ---------- */
		System.out.println("************ Entrez le chiffre correspondant à votre choix : ************");
		System.out.print(String.format("%-20s", "[1] Versement"));
		System.out.print(String.format("%-20s", "[2] Retrait"));
		System.out.println(String.format("%-20s", "[3] Virement"));
		System.out.print(String.format("%-20s", "[4] Info solde"));
		System.out.print(String.format("%-20s", "[5] Historique"));
		System.out.println(String.format("%-20s", "[6] Sortir"));
		
		/* ---------- GESTION SAISIE  ---------- */
		int result = 0;
		
		while (result < 1 || result > 6) {
			result = ReadInt();
		}

		return result;
	}
	
	// Initialise des variables et instances fictives pour tests.
	public static void init() {
		business.createCustomer("albert", "marie", "bjr@fms-ea.com");
		business.createCustomer("fred", "jean", "bjr@fms2.com");
		business.createAccount(0, false);
		business.createAccount(1, false);
		business.createAccount(1, true);
	}
	
	// Invite l'utilisateur à saisir un entier
	public static int ReadInt() {
		while (!scan.hasNextInt())
			scan.next();
		
		return scan.nextInt();
	}
	
	public static void main(String[] args) {
		
		//Creation customer and account + affichage
		System.out.println(business.CreateCustomer("albert", "marie", "bjr@fms-ea.com"));
		System.out.println(business.CreateCustomer("fred", "jean", "bjr@fms2.com"));
		System.out.println(business.CreateAccount(0, false));
		
		System.out.println(business.getCustomerList().get(0));
		System.out.println(business.getAccountList().get(0));
		
		System.out.println(business.CreateAccount(0, false));
		
		
		//Solde - deposit
		System.out.println(business.getAccountList().get(0).getBalance());
		System.out.println(business.Deposit(business.getAccountList().get(0).getAccountID(), 5000));
		System.out.println(business.getAccountList().get(0).getBalance());
		
		//autorisation découvert
		System.out.println(business.Withdraw(business.getAccountList().get(0).getAccountID(), 6000));
		System.out.println(business.getAccountList().get(0).getBalance());
		//((CurrentAccount)(business.getAccountList().get(0))).setAllowedOverdraft(true);
		System.out.println(business.Withdraw(business.getAccountList().get(0).getAccountID(), 6000));
		System.out.println(business.getAccountList().get(0).getBalance());
		
		//virement
		
		System.out.println(business.Transfer(business.getAccountList().get(0).getAccountID(), business.getAccountList().get(1).getAccountID(), 30000));
		System.out.println(business.getAccountList().get(0).getBalance());
		System.out.println(business.getAccountList().get(1).getBalance());
		
		//historique des transactions
		
		System.out.println(business.getAccountList().get(0));

		for (Transaction transcation : BusinessImpl.getTransactionsList()) { 
			System.out.println(transcation);
		}
	}
}