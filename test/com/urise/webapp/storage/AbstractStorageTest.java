package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {
    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "uuidNotExist";
    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;
    private static final Resume RESUME_NOT_EXIST;

    static {
        RESUME_1 = new Resume(UUID_1, "name1");
        RESUME_2 = new Resume(UUID_2, "name2");
        RESUME_3 = new Resume(UUID_3, "name3");
        RESUME_4 = new Resume(UUID_4, "name4");
        RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST, "resumeNotExist");
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(RESUME_NOT_EXIST.getUuid());
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        Resume expected = new Resume(UUID_1, "newName");
        storage.update(expected);
        assertSame(expected, storage.get(expected.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(RESUME_1.getUuid());
        assertSize(2);
        assertGet(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(RESUME_NOT_EXIST.getUuid());
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        List<Resume> expected = new ArrayList<>();
        assertEquals(expected, storage.getAllSorted());
    }

    @Test
    public void getAllSorted() {
        List<Resume> expected = storage.getAllSorted();
        assertEquals(expected, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
        assertSize(3);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume expected) {
        assertEquals(expected, storage.get(expected.getUuid()));
    }
}