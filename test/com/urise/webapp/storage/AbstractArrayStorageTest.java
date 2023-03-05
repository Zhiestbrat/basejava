package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private final Resume resume1 = new Resume("uuid1");
    private final Resume resume2 = new Resume("uuid2");
    private final Resume resume3 = new Resume("uuid3");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        assertEquals(resume1, storage.get(resume1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        Resume expected = new Resume();
        storage.get(expected.getUuid());
    }

    @Test
    public void save() {
        Resume expectedResume = new Resume();
        storage.save(expectedResume);
        assertEquals(expectedResume, storage.get(expectedResume.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
    }

    @Test
    public void update() {
        Resume expectedResume = new Resume();
        storage.save(expectedResume);
        storage.update(expectedResume);
        assertEquals(expectedResume, storage.get(expectedResume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume expected = new Resume();
        storage.update(expected);
    }

    @Test
    public void delete() {
        storage.delete(resume1.getUuid());
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        Resume expected = new Resume();
        storage.delete(expected.getUuid());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] expected = storage.getAll();
        assertArrayEquals(expected, storage.getAll());
    }
}