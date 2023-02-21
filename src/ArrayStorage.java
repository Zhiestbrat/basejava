


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int pointer = 0;

    void clear() {
        for (int i = 0; i < pointer; i++) {
            storage[i] = null;
            pointer = 0;
        }
    }

    void save(Resume r) {
        storage[pointer++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < pointer; i++) {
            if(uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 1; i < pointer; i++) {
            if(uuid.equals(storage[i - 1].uuid)) {
                storage[i - 1] = storage[i];
                storage[i] = storage[i+1];
                storage[pointer] = null;
               pointer--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resume = new Resume[pointer];
        System.arraycopy(storage, 0, resume, 0, pointer);
        return resume;
    }

    int size() {
        return pointer;
    }

}
