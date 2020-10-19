package com.db.browser.dbbrowser.db.dao;

import com.db.browser.dbbrowser.db.DBConnection;
import com.db.browser.dbbrowser.db.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.db.browser.dbbrowser.db.Constants.*;
import static com.db.browser.dbbrowser.db.Utils.*;

@Component
public class TableDataDaoImpl implements TableDataDao {

    @Override
    public List<String> getTables() throws SQLException {
        DatabaseMetaData databaseMetaData = DBConnection.getInstance().getConnection().getMetaData();
        String table[] = {TABLE};
        List<String> tables = new ArrayList();
        ResultSet rs = databaseMetaData.getTables(null, null, null, table);
        while (rs.next()) {
            tables.add(rs.getString(TABLE_NAME));
        }
        return tables;
    }

    @Override
    public List<RowData> getTableData(String table, List<Column> columns) throws SQLException {
        List<RowData> data = new ArrayList();
        String sql = buildQuery(table, columns, table);

        try (PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                RowData row = new RowData();
                List<ColumnData> columnDataList = new ArrayList<>();
                columns.stream().forEach(col -> {
                    String columnName = col.getColumnName();
                    try {
                        ColumnData columnData = new ColumnData();
                        columnData.setColumnName(columnName);
                        columnData.setValue(rs.getString(columnName));
                        columnDataList.add(columnData);
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                });
                row.setColumnDataList(columnDataList);
                data.add(row);
            }
        }
        return Collections.unmodifiableList(data);
    }

    @Override
    public List<Column> getColumnsFromTable(String table) throws SQLException {
        DatabaseMetaData databaseMetaData = DBConnection.getInstance().getConnection().getMetaData();
        ResultSet columnsResultSet = databaseMetaData.getColumns(null, null, table, null);
        List<Column> columns = new ArrayList();
        while (columnsResultSet.next()) {
            Column column = new Column(
                    columnsResultSet.getString(COLUMN_NAME),
                    columnsResultSet.getString(COLUMN_SIZE),
                    columnsResultSet.getString(TYPE_NAME),
                    columnsResultSet.getString(IS_NULLABLE),
                    columnsResultSet.getString(IS_AUTOINCREMENT));
            columns.add(column);
        }
        return Collections.unmodifiableList(columns);
    }

    @Override
    public ColumnMetadata getKeys(String table) throws SQLException {
        DatabaseMetaData databaseMetaData = DBConnection.getInstance().getConnection().getMetaData();
        ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null, null, table);

        ColumnMetadata columnMetadata = new ColumnMetadata();
        setPrimaryKeys(columnMetadata, primaryKeys);

        ResultSet foreignKeys = databaseMetaData.getImportedKeys(null, null, table);
        setForeignKeys(columnMetadata, foreignKeys);

        return columnMetadata;
    }

    @Override
    public ColumnStatistic getColumnStatistic(String sql) throws SQLException {
        DatabaseMetaData databaseMetaData = DBConnection.getInstance().getConnection().getMetaData();
        ColumnStatistic data = new ColumnStatistic();
        try (PreparedStatement stmt = databaseMetaData.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                try {
                    data.setMin(rs.getString(MIN));
                    data.setMax(rs.getString(MAX));
                    data.setAvg(rs.getString(AVG));

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return data;
    }

    @Override
    public List<String> getSchemas() throws SQLException {
        DatabaseMetaData databaseMetaData = DBConnection.getInstance().getConnection().getMetaData();
        ResultSet schemas = databaseMetaData.getCatalogs();
        List<String> list = new ArrayList<>();

        while (schemas.next()) {
            String schema = schemas.getString("TABLE_CAT");
            list.add(schema);
        }
        return list;
    }

    @Override
    public List<TableStatistic> getAllTableStatistic() throws SQLException {
        List<TableStatistic> data = new ArrayList<>();
        List<String> tables = getTables();
        tables.stream().forEach(tableName -> {
            List<Column> columns = null;
            try {
                columns = getColumnsFromTable(tableName);
                int columnsCount = columns.size();
                int numberOfRecords = 0;
                numberOfRecords = getTableData(tableName, columns).size();
                TableStatistic tableStatistic = new TableStatistic(tableName, columnsCount, numberOfRecords);
                data.add(tableStatistic);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
        return data;
    }

}
