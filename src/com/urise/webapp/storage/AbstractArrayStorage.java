package com.urise.webapp.storage;


import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 100000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int count;

    protected int sizeAll() {
        return count;
    }

    protected void clearAll() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    @Override
    protected List<Resume> getAllSorted() {
        return List.of(Arrays.copyOfRange(storage, 0, count));
    }

    @Override
    protected void updateResumeAll(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected Resume getResume(int index) {
        return storage[index];
    }

    @Override
    protected void saveResumeAll(Resume resume, int index) {
        if (count == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            saveResume(resume, index);
            count++;
        }
    }

    @Override
    protected void deleteResumeAll(int index) {
        deleteResume(index);
        storage[count - 1] = null;
        count--;
    }


    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);
}
