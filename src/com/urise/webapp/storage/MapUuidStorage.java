package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> uuidMap = new HashMap<>();

    @Override
    public int size() {
        return uuidMap.size();
    }

    @Override
    public void clear() {
        uuidMap.clear();
    }

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(uuidMap.values());
    }

    @Override
    protected void doUpdate(Resume resume, String searchKey) {
        uuidMap.put(searchKey, resume);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return uuidMap.get(searchKey);
    }

    @Override
    protected void doSave(Resume resume, String searchKey) {
        uuidMap.put(searchKey, resume);
    }

    @Override
    protected void doDelete(String searchKey) {
        uuidMap.remove(searchKey);
    }

    @Override
    protected boolean isExist(String searchKey) {
        return uuidMap.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
