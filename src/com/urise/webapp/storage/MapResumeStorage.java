package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(resumeMap.values());
    }

    @Override
    protected void doUpdate(Resume resume, Resume searchKey) {
        resumeMap.put(searchKey.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Resume searchKey) {
        return resumeMap.get(searchKey.getUuid());
    }

    @Override
    protected void doSave(Resume resume, Resume searchKey) {
        resumeMap.put(searchKey.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume searchKey) {
        resumeMap.remove(searchKey.getUuid());
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return resumeMap.containsKey(searchKey.getUuid());
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return new Resume(uuid);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
