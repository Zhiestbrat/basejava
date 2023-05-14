package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.urise.webapp.ResumeTestData.getResume;
import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected final static File STORAGE_DIR = Config.getInstance().getStorageDir();
    protected final Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final Resume R1 = getResume(UUID_1, "Name1");
    private static final Resume R2 = getResume(UUID_2, "Name2");
    private static final Resume R3 = getResume(UUID_3, "Name3");
    private static final Resume R4 = getResume(UUID_4, "Name4");


    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(R4.getUuid());
    }

    @Test
    public void save() {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R1);
    }

    @Test
    public void update() {
        Resume expected = new Resume(UUID_1, "newName");
        storage.update(expected);
        assertEquals(expected, storage.get(expected.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(R4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(R1.getUuid());
        assertSize(2);
        assertGet(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(R4.getUuid());
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
        List<Resume> sortedResumes = storage.getAllSorted();
        assertEquals(Arrays.asList(R1, R2, R3), sortedResumes);
        assertSize(3);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume expected) {
        assertEquals(expected, storage.get(expected.getUuid()));
    }
}