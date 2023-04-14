package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author p.kondakov
 */
public class MainFile {
    public static void main(String[] args) {
        File file = new File(".\\.gitignore");
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File(".\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String str : list) {
                System.out.println(str);
            }
        }
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            System.out.println(fileInputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        walk(dir, "");
    }

    public static void walk(File dir, String offset) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(offset + "F: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(offset + "D: " + file.getName());
                    walk(file, offset + "  ");
                }
            }
        }
    }
}
