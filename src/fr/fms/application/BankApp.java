package fr.fms.application;

import fr.fms.business.BusinessImpl;
import fr.fms.entities.CurrentAccount;
import fr.fms.entities.Transaction;

public class BankApp {
	public static BusinessImpl business = new BusinessImpl();
	
	public static void main(String[] args) {
		
		//Creation customer and account + affichage
		System.out.println(business.createCustomer("albert", "marie", "bjr@fms-ea.com"));
		System.out.println(business.createCustomer("fred", "jean", "bjr@fms2.com"));
		System.out.println(business.createAccount(0, false));
		
		System.out.println(business.getCustomerList().get(0));
		System.out.println(business.getAccountList().get(0));
		
		System.out.println(business.createAccount(0, false));
		
		
		//Solde - deposit
		System.out.println(business.getAccountList().get(0).getBalance());
		System.out.println(business.operation(business.getAccountList().get(0).getAccountID(), 5000));
		System.out.println(business.getAccountList().get(0).getBalance());
		
		//autorisation découvert
		System.out.println(business.operation(business.getAccountList().get(0).getAccountID(), -6000));
		System.out.println(business.getAccountList().get(0).getBalance());
		((CurrentAccount)(business.getAccountList().get(0))).setAllowedOverdraft(true);
		System.out.println(business.operation(business.getAccountList().get(0).getAccountID(), -6000));
		System.out.println(business.getAccountList().get(0).getBalance());
		
		//virement
		
		System.out.println(business.transfer(business.getAccountList().get(0).getAccountID(), business.getAccountList().get(1).getAccountID(), 300));
		System.out.println(business.getAccountList().get(0).getBalance());
		System.out.println(business.getAccountList().get(1).getBalance());
		
		//historique des transactions
		
		System.out.println(business.getAccountList().get(0));

		for (Transaction transcation : business.getTransactionList()) { 
			System.out.println(transcation);
		}
	}
}