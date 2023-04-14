package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.XmlStreamSerializer;

/**
 * @author p.kondakov
 */
public class XmlStreamPathStorageTest extends AbstractStorageTest {
    public XmlStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}
