package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.List;


public abstract class AbstractStorage<E> implements Storage {
    public final Resume get(String uuid) {
        getNotExistingSearchKey(uuid);
        return doGet(getSearchKey(uuid));
    }

    public final List<Resume> getAll() {
        return getAllSorted();
    }

    public final void save(Resume resume) {
        getExistingSearchKey(resume.getUuid());
        doSave(resume, getSearchKey(resume.getUuid()));
    }

    public final void update(Resume resume) {
        getNotExistingSearchKey(resume.getUuid());
        doUpdate(resume, getSearchKey(resume.getUuid()));
    }

    public final void delete(String uuid) {
       getNotExistingSearchKey(uuid);
       doDelete(getSearchKey(uuid));
    }
    private void getNotExistingSearchKey(String uuid) {
        E key = getSearchKey(uuid);
        if(!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
    }
    private void getExistingSearchKey(String uuid) {
        E key = getSearchKey(uuid);
        if(isExist(key)) {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract List<Resume> getAllSorted();

    protected abstract void doUpdate(Resume resume, E searchKey);

    protected abstract Resume doGet(E searchKey);

    protected abstract void doSave(Resume resume, E searchKey);

    protected abstract void doDelete(E searchKey);

    protected abstract boolean isExist(E searchKey);
    protected abstract E getSearchKey(String uuid);
}
