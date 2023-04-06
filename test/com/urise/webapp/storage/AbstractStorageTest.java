package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.urise.webapp.ResumeTestData.getResume;
import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected final static File STORAGE_DIR = new File("C:\\Users\\Pavel\\IdeaProjects\\basejava\\storage");

    protected final Storage storage;
    private static final Resume R1 = getResume("uuid1", "name1");
    private static final Resume R2 = getResume("uuid2", "name2");
    private static final Resume R3 = getResume("uuid3", "name3");
    private static final Resume R4 = getResume("uuid4", "name4");
    private static final Resume RESUME_NOT_EXIST = getResume("uuidNotExist", "nameNotExist");

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
        storage.get(RESUME_NOT_EXIST.getUuid());
    }

    @Test
    public void save() {
        storage.save(R4);
        assertGet(R4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R1);
    }

    @Test
    public void update() {
        Resume expected = new Resume("uuid1", "newName");
        storage.update(expected);
        assertEquals(expected, storage.get(expected.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(R1.getUuid());
        assertSize(2);
        assertGet(R1);
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
        assertEquals(expected, Arrays.asList(R1, R2, R3));
        assertSize(3);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume expected) {
        assertEquals(expected, storage.get(expected.getUuid()));
    }
}