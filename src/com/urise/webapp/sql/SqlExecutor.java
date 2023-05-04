package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author p.kondakov
 */
public interface SqlExecutor<T>{
    T execute(PreparedStatement preparedStatement) throws SQLException;
}
