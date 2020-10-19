package com.db.browser.dbbrowser.service;

import com.db.browser.dbbrowser.db.model.*;

import java.sql.SQLException;
import java.util.List;

public interface TableDataService {
    List<String> getTables() throws SQLException;

    List<RowData> getTableData(String table, List<Column> columns) throws Exception;

    List<Column> getColumnsFromTable(String table) throws SQLException;

    ColumnMetadata getKeys(String table) throws SQLException;

    ColumnStatistic getColumnStatistic(String table, String column) throws SQLException;

    List<TableStatistic> getAllTableStatistic() throws Exception;

    List<String> getSchemas() throws SQLException;
}
