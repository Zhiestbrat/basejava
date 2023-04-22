package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author p.kondakov
 */
public class MainConcurrency {
    public static int counter;

    public static final  Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();


        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

       final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(counter);
    }

    private synchronized void inc() {
        //synchronized (this) {}
        //synchronized (MainConcurrency.class){};  ставим синхронайз блок на статическом методе, лочим класс
        counter++;

    }
}
