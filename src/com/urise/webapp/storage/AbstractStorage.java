package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.List;

public abstract class AbstractStorage implements Storage {

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    public final List<Resume> getAll() {
        return getAllSorted();
    }

    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResumeAll(resume, index);
        }
    }

    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResumeAll(resume, index);
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResumeAll(index);
        }
    }

    public final void clear() {
        clearAll();
    }

    public final int size() {
        return sizeAll();
    }
    protected abstract int sizeAll();

    protected abstract void clearAll();

    protected abstract List<Resume> getAllSorted();

    protected abstract void updateResumeAll(Resume resume, int index);

    protected abstract Resume getResume(int index);

    protected abstract void saveResumeAll(Resume resume, int index);

    protected abstract void deleteResumeAll(int index);

    protected abstract int getIndex(String uuid);
}
