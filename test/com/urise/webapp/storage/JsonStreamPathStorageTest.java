package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.JsonStreamSerializer;

/**
 * @author p.kondakov
 */
public class JsonStreamPathStorageTest extends AbstractStorageTest {
    public JsonStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
