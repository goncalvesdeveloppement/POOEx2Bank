package fr.fms.exercices.adv2;
public class TestThread extends Thread {
    public TestThread(String name) {
        super(name);
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.print(this.getName() + " ");
        }
    }

    public static void main(String[] args) {
        TestThread t1 = new TestThread("1-");
        TestThread t2 = new TestThread("2--");
        TestThread t3 = new TestThread("3---");
        TestThread t4 = new TestThread("4----");
        TestThread t5 = new TestThread("5-----");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    // Exercice 2.1 : le résultat diffère d'une exécution à l'autre selon l'ordonnancement des processus par le système d'exploitation, notamment influencé par les processus extérieurs au JRE.
}
