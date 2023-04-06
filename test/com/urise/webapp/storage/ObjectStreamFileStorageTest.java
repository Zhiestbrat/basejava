package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.ObjectStreamStorage;

/**
 * @author p.kondakov
 */
public class ObjectStreamFileStorageTest extends AbstractStorageTest {
    public ObjectStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }
}
