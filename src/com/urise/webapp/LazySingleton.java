package com.urise.webapp;

/**
 * @author p.kondakov
 */
public class LazySingleton {
    private static LazySingleton INSTANCE;

    private LazySingleton() {
    }

    private static class LazySingletonHolder{
        private static final LazySingleton INSTANCE = new LazySingleton();
    }
//    public static LazySingleton getInstance() {
//        if (INSTANCE == null) {
//            synchronized (LazySingleton.class) { //Double-checked locking
//                if (INSTANCE == null) {
//                    INSTANCE = new LazySingleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }

    public static LazySingleton getInstance(){
        return LazySingletonHolder.INSTANCE;
    }
}
