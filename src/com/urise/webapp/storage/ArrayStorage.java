package com.urise.webapp.storage;


import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[100000];
    private int count;

    public void clear() {
        Arrays.fill(storage,0,count, null);
        count = 0;
    }

    public void update(Resume resume) {
        for (int i = 0; i < count; i++) {
            if (resume.equals(storage[i])) {
                storage[i] = resume;
            }
        }
        System.out.println("ERROR : update");
    }

    public void save(Resume r) {
        if (!r.equals(storage[count])) {
            storage[count++] = r;
        } else {
            System.out.println("ERROR : save");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        System.out.println("ERROR : get");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                storage[i] = storage[count - 1];
                storage[count - 1] = null;
                count--;
                break;
            }
        }
        System.out.println("ERROR : delete");
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
