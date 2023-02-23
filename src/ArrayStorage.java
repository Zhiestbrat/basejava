


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int count;

    void clear() {
        for (int i = 0; i < count; i++) {
            storage[i] = null;
        }
        count = 0;
    }

    void save(Resume r) {
        storage[count++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].uuid)) {
                storage[i] = storage[count - 1];
                storage[count - 1] = null;
                count--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resume = new Resume[count];
        System.arraycopy(storage, 0, resume, 0, count);
        return resume;
    }

    int size() {
        return count;
    }
}
