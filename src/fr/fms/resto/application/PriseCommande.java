package fr.fms.resto.application;
import jdk.jfr.events.FileWriteEvent;
import resto.tp.entities.Carte;
import resto.tp.entities.Produit;

import java.awt.Menu;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PriseCommande {
	public static String writeResult = "";
	public static double prixTotal = 0d;

	public static void main(String[] args) {
		fr.fms.resto.entities.Carte carte = new fr.fms.resto.entities.Carte();
		int nbMenus = 0;

		ArrayList<String> nomsProduits = new ArrayList<String>();
		String typesProduits = "EEEEPPPPPPAAAAABBBBBDDDD";

		nomsProduits.add("Salade");
		nomsProduits.add("Soupe");
		nomsProduits.add("Quiche");
		nomsProduits.add("Aucune entrée");
		nomsProduits.add("Poulet");
		nomsProduits.add("Boeuf");
		nomsProduits.add("Poisson");
		nomsProduits.add("Végétarien");
		nomsProduits.add("Végan");
		nomsProduits.add("Pas de plat");
		nomsProduits.add("Riz");
		nomsProduits.add("Pâtes");
		nomsProduits.add("Frites");
		nomsProduits.add("Légumes");
		nomsProduits.add("Aucun accompagnement");
		nomsProduits.add("Eau plate");
		nomsProduits.add("Eau gazeuse");
		nomsProduits.add("Soda");
		nomsProduits.add("Vin");
		nomsProduits.add("Aucune boisson");
		nomsProduits.add("Tarte maison");
		nomsProduits.add("Mousse au chocolat");
		nomsProduits.add("Tiramisu");
		nomsProduits.add("Pas de dessert");

		for (int i = 0; i < nomsProduits.size(); i++) {
			carte.NouveauProduit(nomsProduits.get(i), typesProduits.substring(i, i + 1),  0.5 * Math.floor(1 + (Math.random() * 9)));
		}

		nbMenus = SaisieEntrees.SaisieEntier("Bonjour, combien de menus souhaitez-vous ?");

		for (int menuActuel = 0; menuActuel < nbMenus; menuActuel++) {
			SaisieMenu(carte, menuActuel + 1);
		}

		writeResult += "\n************ TOTAL : " + String.format("%.2f", prixTotal) + " € ************";

		try {
			FileWriter writer = new FileWriter("order.txt");
			writer.write(writeResult);
			writer.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void SaisieMenu(fr.fms.resto.entities.Carte carte, int numeroCommande) {
		fr.fms.resto.entities.Menu menu = new fr.fms.resto.entities.Menu();
		String[] typesProduits = { "entrée", "plat", "accompagnement", "boisson", "dessert" };
		int choixActuel = 0;
		ArrayList<fr.fms.resto.entities.Produit> produitsCategorieActuelle = new ArrayList<fr.fms.resto.entities.Produit>();

		for (int typeProduit = 0; typeProduit < 5; typeProduit++) {
			choixActuel = 0;
			produitsCategorieActuelle.removeAll(produitsCategorieActuelle);

			System.out.println("Choix " + typesProduits[typeProduit] + " : ");

			int index = 0;

			for (fr.fms.resto.entities.Produit produit : carte.produits) {
				if (produit.type.equals(typesProduits[typeProduit].substring(0, 1).toUpperCase())) {
					index++;
					System.out.print("[" + index + " - " + produit.nom.toUpperCase() + "]");
					produitsCategorieActuelle.add(produit);
				}
			}

			while (choixActuel < 1 || choixActuel > index) {
				choixActuel = SaisieEntrees.SaisieEntier("\nQue souhaitez-vous comme " + typesProduits[typeProduit]
						+ " (saisir le chiffre correspondant)");
			}

			menu.produits[typeProduit] = produitsCategorieActuelle.get(choixActuel - 1);
		}

		writeResult += ("************ Résumé de la commande n°" + numeroCommande + " ************\n");
		double prixMenu = 0d;

		for (fr.fms.resto.entities.Produit produit : menu.produits) {
			writeResult += (produit.nom + " — " + String.format("%.2f", produit.prix) + " €" + "\n");
			prixMenu += produit.prix;
		}

		writeResult += ("\nPrix de la commande n°" + numeroCommande + " : " + String.format("%.2f", prixMenu) + " €\n\n");
		prixTotal += prixMenu;
	}

}
