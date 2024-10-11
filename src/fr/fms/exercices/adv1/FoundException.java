package fr.fms.exercices.adv1;
import java.util.Date;

// Exercice 1.1
public class FoundException {
    public static void main(String[] args) {
        Date date = null;
        Date today = new Date();

        displayDateObjectClassName(date);
        displayDateObjectClassName(today);
    }

    // Affiche le nom de classe d'un objet Date
    public static void displayDateObjectClassName(Date date) {
        try {
            System.out.println(date.getClass().getName());
        } catch (NullPointerException npe) {
            System.out.println("Exception due à l'affichage d'un objet ayant pour valeur : " + npe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // La méthode getClass() d'un objet renvoie une classe correspondant à celle de niveau le plus superficiel à laquelle appartient l'objet.
}
