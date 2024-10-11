package fr.fms.exercices.adv2;
public class TestRunnable implements Runnable {
    // Exercice 2.2
	
	@Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.print((char) (i + 32));

            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }

            System.out.println((char) (i + 32));
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new TestRunnable());
        thread.start();
    }
}
