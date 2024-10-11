package fr.fms.resto.application;
import java.util.Scanner;

public class SaisieEntrees {

	public static Scanner scanner = new Scanner(System.in);

	public static int SaisieEntier(String message) {
		boolean nombreOK = false;
		int resultat = -1;
		
		System.out.print(message + " : ");

		while (!nombreOK) {
			if (scanner.hasNextInt()) {
				resultat = scanner.nextInt();
				nombreOK = true;
			} else
				System.out.println("Erreur de saisie : la valeur doit être un nombre entier.");
			scanner = new Scanner(System.in);
		}
		
		return resultat;
	}
	
	public static double SaisieDecimal(String message) {
		boolean nombreOK = false;
		double resultat = -0.1;
		
		System.out.print(message + " : ");

		while (!nombreOK) {
			if (scanner.hasNextDouble()) {
				resultat = scanner.nextDouble();
				nombreOK = true;
			} else
				System.out.println("Erreur de saisie : la valeur doit être un nombre.");
			scanner = new Scanner(System.in);
		}
		
		return resultat;
	}

	public static String SaisieChaineTexte(String message) {
		String resultat = "";
		
		System.out.print(message + " : ");

		resultat = scanner.nextLine();
		
		return resultat.length() > 0 ? resultat : "";
	}
	
	public static boolean QuestionOuiNon(String message) {
		System.out.print(message + " (O/N) : ");
		String resultat = "X";

		do {
			scanner = new Scanner(System.in);
			resultat = scanner.nextLine();

			if (resultat.length() > 0)
				resultat = resultat.toUpperCase().substring(0, 1);
		} while (!resultat.equals("O") && !resultat.equals("N"));

		return resultat.equals("O");
	}

}