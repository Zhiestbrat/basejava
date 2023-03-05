package com.urise.webapp.storage;


public class ArrayStorageTest extends AbstractArrayStorageTest {
    Storage arrayStorage;

    public ArrayStorageTest() {
        super(new ArrayStorage());
        this.arrayStorage = new ArrayStorage();
    }
}