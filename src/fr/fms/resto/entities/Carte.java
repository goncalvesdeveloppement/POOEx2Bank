package fr.fms.resto.entities;
import java.util.ArrayList;

public class Carte {
	public ArrayList<Produit> produits = new ArrayList<Produit>();

	public void NouveauProduit(String nom, String type, double prix) {
		Produit produit = new Produit();
		
		produit.nom = nom;
		produit.type = type;
		produit.prix = prix;
		
		produits.add(produit);
	}
}