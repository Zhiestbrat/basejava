package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.List;


public abstract class AbstractStorage<E> implements Storage {
    public final Resume get(String uuid) {
        E key = getNotExistingSearchKey(uuid);
        return doGet(key);
    }

    public final List<Resume> getAll() {
        return getAllSorted();
    }

    public final void save(Resume resume) {
        E keySearch = getExistingSearchKey(resume.getUuid());
        doSave(resume, keySearch);
    }

    public final void update(Resume resume) {
        E key = getNotExistingSearchKey(resume.getUuid());
        doUpdate(resume, key);
    }

    public final void delete(String uuid) {
       E key = getNotExistingSearchKey(uuid);
       doDelete(key);
    }
    private E getNotExistingSearchKey(String uuid) {
        E key = getSearchKey(uuid);
        if(!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }
    private E getExistingSearchKey(String uuid) {
        E key = getSearchKey(uuid);
        if(isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract List<Resume> getAllSorted();

    protected abstract void doUpdate(Resume resume, E searchKey);

    protected abstract Resume doGet(E searchKey);

    protected abstract void doSave(Resume resume, E searchKey);

    protected abstract void doDelete(E searchKey);

    protected abstract boolean isExist(E searchKey);
    protected abstract E getSearchKey(String uuid);
}
