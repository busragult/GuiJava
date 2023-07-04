package com.company;

import java.util.Arrays;

class MyThread implements Runnable {
    String name;
    Thread t;

    MyThread(String thread) {
        this.name = thread;
        t = new Thread(this, name);
        t.start();
    }

    public void run() {
        long elapsedTime, start, end;
        start = System.currentTimeMillis();
        do {
            if (Arrays.stream(BusraGultekinS23823.letters).anyMatch(x -> x.equals(name)) && !BusraGultekinS23823.letters[BusraGultekinS23823.suspend].equals(name)) {
                BusraGultekinS23823.mainThread.name += name;
            }
            try {
                Thread.sleep((long) (Math.random() % 1000) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            end = System.currentTimeMillis();
            elapsedTime = end - start;
        } while (elapsedTime < ((BusraGultekinS23823.loop * 5000) + ((long) (Math.random() * 1000) + 100)));
        if (Arrays.stream(BusraGultekinS23823.letters).anyMatch(x -> x.equals(name)))
            System.out.println("Thread " + name + " exits");
    }
}

public class BusraGultekinS23823 {

    static String[] letters = {"a", "b", "c", "d", "e"};
    static MyThread mainThread = new MyThread("");
    static int suspend = 0;
    static long loop = 10;

    public static void main(String[] args) {

        MyThread[] t = new MyThread[5];

        for (int i = 0; i < t.length; i++) {
            t[i] = new MyThread(letters[i]);
        }

        try {
            for (int i = 0; i < loop; i++) {
                Thread.sleep(5000);
                System.out.println(mainThread.name);
                System.out.print("Resuming " + letters[suspend] + ", suspending " + letters[(suspend + 1) % letters.length] + ": ");
                BusraGultekinS23823.suspend = (BusraGultekinS23823.suspend + 1) % BusraGultekinS23823.letters.length;
                mainThread.name = "";
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
    }
}
