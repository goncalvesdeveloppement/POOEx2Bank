package fr.fms.application;

import fr.fms.business.BusinessImpl;
import fr.fms.entities.CurrentAccount;

public class BankApp {
	public static BusinessImpl business = new BusinessImpl();
	
	public static void main(String[] args) {
		System.out.println(business.CreateCustomer("albert", "marie", "bjr@fms-ea.com"));
		System.out.println(business.CreateCustomer("fred", "jean", "bjr@fms2.com"));
		System.out.println(business.CreateAccount(0, false));
		
		System.out.println(business.getCustomerList().get(0));
		System.out.println(business.getAccountList().get(0));
		
		System.out.println(business.CreateAccount(0, false));
	}
}