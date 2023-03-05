package com.urise.webapp.storage;


public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    Storage storageSorted;

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
        this.storageSorted = new SortedArrayStorage();
    }
}