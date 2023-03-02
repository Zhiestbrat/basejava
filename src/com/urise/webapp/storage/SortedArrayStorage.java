package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;


public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    protected int getIndex(String uuid) {
        Resume key = new Resume();
        key.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, count, key);
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        int insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, count - insertIdx);
        storage[insertIdx] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        int num = count - index - 1;
        if(num > 0) {
            System.arraycopy(storage, index + 1, storage, index, num);
        }
    }
}
