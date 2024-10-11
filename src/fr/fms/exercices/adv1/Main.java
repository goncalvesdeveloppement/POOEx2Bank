package fr.fms.exercices.adv1;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bonjour. Entrez un nombre entier entre -1000 et 1000 :");

        try {
            Analyse(scanInt());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void Analyse(int nombre) {
        System.out.println(
                nombre + " est un nombre entier " + (nombre == 0 ? "neutre" : (nombre > 0 ? "positif" : "négatif"))
                        + " et " + (nombre % 2 == 0 ? "pair" : "impair"));
    }

    private static int scanInt() throws OutOfProgramBoundsException, InputMismatchException {
        while (!scanner.hasNextInt())
            scanner.next();

        int result = scanner.nextInt();

        if (result < -1000 || result > 1000)
            throw new OutOfProgramBoundsException("Le nombre n'est pas entre -1000 et 1000 !");

        return result;
    }
}