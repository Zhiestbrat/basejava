package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> listResume = new ArrayList<>();

    @Override
    public void clear() {
        listResume.clear();
    }

    @Override
    public int size() {
        return listResume.size();
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(listResume);
    }

    @Override
    protected void doUpdate(Resume resume, Integer searchKey) {
        listResume.set(searchKey, resume);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return listResume.get(searchKey);
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        listResume.add(resume);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        listResume.remove(listResume.get(searchKey));
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < listResume.size(); i++) {
            if (uuid.equals(listResume.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }
}
