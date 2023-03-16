package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;


public abstract class AbstractStorage<E> implements Storage {
    protected abstract List<Resume> getList();

    protected abstract void doUpdate(Resume resume, E searchKey);

    protected abstract Resume doGet(E searchKey);

    protected abstract void doSave(Resume resume, E searchKey);

    protected abstract void doDelete(E searchKey);

    protected abstract boolean isExist(E searchKey);

    protected abstract E getSearchKey(String uuid);

    public final Resume get(String uuid) {
        E searchKey = getExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    public final List<Resume> getAllSorted() {
        List<Resume> listResume = getList();
        listResume.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return listResume;
    }

    public final void save(Resume resume) {
        E searchKey = getNotExistingSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public final void update(Resume resume) {
        E searchKey = getExistingSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    public final void delete(String uuid) {
        E searchKey = getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    private E getNotExistingSearchKey(String uuid) {
        E searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private E getExistingSearchKey(String uuid) {
        E searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
