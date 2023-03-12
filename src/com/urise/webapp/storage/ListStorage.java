package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> listResume = new ArrayList<>();

    @Override
    protected void clearAll() {
        listResume.clear();
    }

    @Override
    protected int sizeAll() {
        return listResume.size();
    }

    @Override
    protected List<Resume> getAllSorted() {
        return listResume;
    }

    @Override
    protected void updateResumeAll(Resume resume, int index) {
        listResume.set(index, resume);
    }

    @Override
    protected Resume getResume(int index) {
        return listResume.get(index);
    }

    @Override
    protected void saveResumeAll(Resume resume, int index) {
        listResume.add(resume);
    }

    @Override
    protected void deleteResumeAll(int index) {
        listResume.remove(index);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < listResume.size(); i++) {
            if (uuid.equals(listResume.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
