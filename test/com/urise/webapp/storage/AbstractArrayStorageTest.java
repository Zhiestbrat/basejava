package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "uuidNotExist";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        assertSize(3);
    }

    public void assertSize(int expectedSize) {
        assertEquals(expectedSize, storage.size());
    }

    @Test
    public void get() {
        assertGet(new Resume(UUID_1));
        assertGet(new Resume(UUID_2));
        assertGet(new Resume(UUID_3));
    }

    public void assertGet(Resume expected) {
        assertEquals(expected, storage.get(expected.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(new Resume(UUID_NOT_EXIST).getUuid());
    }

    @Test
    public void save() {
        storage.save(new Resume(UUID_4));
        assertGet(new Resume(UUID_4));
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        for (int i = 0; i < STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        storage.save(new Resume(UUID_4));
    }

    @Test
    public void update() {
        Resume expected = storage.get(UUID_1);
        assertSame(expected, storage.get(expected.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(UUID_NOT_EXIST));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(new Resume(UUID_1).getUuid());
        assertSize(2);
        assertGet(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(new Resume(UUID_NOT_EXIST).getUuid());
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Resume[] expected = new Resume[0];
        assertArrayEquals(expected, storage.getAll());
    }

    @Test
    public void getAll() {
        Resume[] expected = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(expected, storage.getAll());
        assertSize(3);
    }
}