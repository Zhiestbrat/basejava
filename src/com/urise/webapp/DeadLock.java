package com.urise.webapp;

/**
 * @author p.kondakov
 */
public class DeadLock {
    public static void main(String[] args) {
         Account account1 = new Account();
         Account account2 = new Account();

       Thread thread1 =  new Thread(()-> {
           synchronized (account1) {
               synchronized (account2) {
                   Account.transfer(account1, account2, 1000);
               }
           }
       });
       Thread thread2 =  new Thread(()-> {
           synchronized (account2) {
               synchronized (account1) {
                   Account.transfer(account2, account1, 1000);
               }
           }
       });
       thread1.start();
       thread2.start();
    }

    static class Account {
        private static int balance = 10000;

        public void deposit(int amount) {
            balance += amount;
        }

        public  void withdraw(int amount) {
            balance -= amount;
        }
        public static void transfer(Account account1, Account account2, int amount) {
            account1.withdraw(amount);
            account2.deposit(amount);
        }
    }
}
