package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.ObjectStreamStorage;

/**
 * @author p.kondakov
 */
public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamStorage()));
    }
}
