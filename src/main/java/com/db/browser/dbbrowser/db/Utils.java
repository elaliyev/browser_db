package com.db.browser.dbbrowser.db;

import com.db.browser.dbbrowser.db.model.Column;
import com.db.browser.dbbrowser.db.model.ColumnMetadata;
import com.db.browser.dbbrowser.model.Connection;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static com.db.browser.dbbrowser.db.Constants.*;

public class Utils {

    public static DataSource getDataSource(Connection connection) throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        String dbName = connection.getDbName();
        dataSource.setDatabaseName(dbName);
        dataSource.setUrl(connection.getUrl());
        dataSource.setServerName(connection.getHostname());
        dataSource.setPort(connection.getPort());
        dataSource.setUser(connection.getUsername());
        dataSource.setPassword(connection.getPassword());
        dataSource.setServerTimezone("UTC");
        return dataSource;
    }

    public static String buildQuery(String table, List<Column> columns, String table1) {
        StringBuilder columnsNames = new StringBuilder(
                columns.stream()
                        .map(col -> col.getColumnName())
                        .collect(Collectors.joining(DELIMITER)));

        StringBuilder sql = new StringBuilder(SELECT);
        sql.append(SPACE).append(columnsNames).append(SPACE).append(FROM).append(SPACE).append(table);
        return sql.toString();
    }


    public static void setPrimaryKeys(ColumnMetadata columnMetadata, ResultSet primaryKeys) throws SQLException {
        while (primaryKeys.next()) {
            String primaryKeyColumnName = primaryKeys.getString(COLUMN_NAME);
            String primaryKeyName = primaryKeys.getString(PK_NAME);
            columnMetadata.setPrimaryKeyColumnName(primaryKeyColumnName);
            columnMetadata.setPrimaryKeyName(primaryKeyName);
        }
    }

    public static void setForeignKeys(ColumnMetadata columnMetadata, ResultSet foreignKeys) throws SQLException {
        while (foreignKeys.next()) {
            String pkTableName = foreignKeys.getString(PKTABLE_NAME);
            String fkTableName = foreignKeys.getString(FKTABLE_NAME);
            String pkColumnName = foreignKeys.getString(PKCOLUMN_NAME);
            String fkColumnName = foreignKeys.getString(FKCOLUMN_NAME);

            columnMetadata.setPkTableName(pkTableName);
            columnMetadata.setFkTableName(fkTableName);
            columnMetadata.setPkColumnName(pkColumnName);
            columnMetadata.setFkColumnName(fkColumnName);
        }
    }

    public static String applyAggregatorFunctions(String column) {
        StringBuilder builder = new StringBuilder();
        builder.append("min(").append(column).append(")").append(" as min");
        builder.append(DELIMITER);
        builder.append("max(").append(column).append(")").append(" as max");
        builder.append(DELIMITER);
        builder.append("avg(").append(column).append(")").append(" as avg");

        return builder.toString();
    }

}
