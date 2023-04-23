package com.urise.webapp;

/**
 * @author p.kondakov
 */
public class DeadLock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronize(lock1, lock2);
            System.out.println(Thread.currentThread().getName() + " end");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronize(lock2, lock1);
            System.out.println(Thread.currentThread().getName() + " end");
        });
        thread1.start();
        thread2.start();
    }

    public static void synchronize(Object lock1, Object lock2){
        synchronized (lock1) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2) {

            }
        }
    }
}
