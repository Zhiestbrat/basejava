package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;


public class SortedArrayStorage extends AbstractArrayStorage {
    public void save(Resume resume) {
        if (count == STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("Resume " + resume.getUuid() + " already exist");
        } else {
            storage[count] = resume;
            count++;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume key = new Resume();
        key.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, count, key);
    }
}
