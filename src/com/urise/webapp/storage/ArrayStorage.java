package com.urise.webapp.storage;


import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[100000];
    private int count;

    public int getIndex(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("ERROR : update");
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            System.out.println("ERROR : no resume");
        } else {
            storage[count++] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR : get");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR : delete");
        } else {
            storage[index] = storage[count - 1];
            storage[count - 1] = null;
            count--;
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resume = new Resume[count];
        System.arraycopy(storage, 0, resume, 0, count);
        return resume;
    }

    public int size() {
        return count;
    }
}
