package com.urise.webapp;

import com.urise.webapp.exception.StorageException;

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

        walk("C:\\Users\\Pavel\\IdeaProjects\\basejava");
    }

    public static void walk(String directory) {
        File root = new File(directory);
        File[] files = root.listFiles();

        if(files == null) {
            throw new StorageException("files error", directory);
        }

        for (File file : files) {
            if(file.isDirectory()) {
                walk(file.getAbsolutePath());
                System.out.println("Dir: " + file.getAbsolutePath());
            } else {
                System.out.println("File: " + file.getAbsolutePath());
            }
        }
    }
}
