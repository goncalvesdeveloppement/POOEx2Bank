package fr.fms.resto.entities;

public class Menu {
	
	public Produit [] produits = new Produit[5];
	
	public double GetPrix() {
		double prix = 0d;
		
		for (Produit produitActuel : produits) {
			prix += produitActuel.prix;
		}
		
		return prix;
	}
	
	public void AjouterProduit(Produit produit) {
		String chaineCategories = "EPABD";
		int numCategorieProduit = chaineCategories.indexOf(produit.type);
		
		produits[numCategorieProduit] = produit;
	}
}
