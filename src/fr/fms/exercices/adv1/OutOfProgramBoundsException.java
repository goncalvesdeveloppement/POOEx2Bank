package fr.fms.exercices.adv1;
public class OutOfProgramBoundsException extends Exception {
    public OutOfProgramBoundsException(String msg) {
        super(msg);
    }

    public OutOfProgramBoundsException() {
        super("Le nombre n'est pas entre -1000 et 1000 !");
    }
}
