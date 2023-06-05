package com.urise.webapp;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author p.kondakov
 */
public class MainConcurrency {
    public static final int THREAD_NUMBER = 10000;
    private static int counter;
    private final AtomicInteger atomicInteger = new AtomicInteger();
//    private static final Object LOCK = new Object();


    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();
    private static final Lock READ_LOCK = reentrantReadWriteLock.readLock();

    private static final ThreadLocal<DateTimeFormatter> threadLocal = ThreadLocal.withInitial(() -> DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                //throw new IllegalStateException();
            }
        };
        thread0.start();


        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREAD_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//

        //  List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_NUMBER; i++) {
            executorService.submit(() -> {
                //Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get());
                }
                latch.countDown();
                return counter;
            });
//            thread.start();
//            //threads.add(thread);
        }
        /*threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });*/
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdownNow();
//        System.out.println(counter);
        System.out.println(mainConcurrency.atomicInteger.get());
    }

    private void inc() {
        //synchronized (this) {}
        //synchronized (MainConcurrency.class){};  ставим синхронайз блок на статическом методе, лочим класс
        atomicInteger.incrementAndGet();
    }
}
