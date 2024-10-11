package fr.fms.exercices.adv2;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class ThreadTime {
	// Exercice 2.3
	
    public static void main(String[] args) {
        Thread thread = new Thread(new MonRunnable(1000));
        thread.start();
    }

    private static class MonRunnable implements Runnable {

        private long delai;

        public MonRunnable(long delai) {
            this.delai = delai;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    String currentTime = df.format(new Date());
					System.out.print("\b \r" + currentTime);
                    Thread.sleep(delai);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}