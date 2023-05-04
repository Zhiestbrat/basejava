package com.urise.webapp.storage;

import com.urise.webapp.Config;


/**
 * @author p.kondakov
 */
public class SqlStorageTest extends AbstractStorageTest{

    public SqlStorageTest() {
        super(Config.getInstance().getStorage());
    }
}