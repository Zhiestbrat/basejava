package com.urise.webapp.storage;

/**
 * @author p.kondakov
 */
public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new AbstractPathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamStorage()));
    }
}
