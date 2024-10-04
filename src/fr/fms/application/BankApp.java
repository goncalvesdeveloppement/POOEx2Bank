package fr.fms.application;

import fr.fms.business.BusinessImpl;
import fr.fms.entities.CurrentAccount;

public class BankApp {
	public static BusinessImpl business = new BusinessImpl();
	
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
		
		
	}
}