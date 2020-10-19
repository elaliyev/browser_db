package com.db.browser.dbbrowser.db.dao;

import com.db.browser.dbbrowser.db.model.*;

import java.sql.SQLException;
import java.util.List;

public interface TableDataDao {

    List<String> getTables() throws SQLException;

    List<RowData> getTableData(String table, List<Column> columns) throws Exception;

    List<Column> getColumnsFromTable(String table) throws SQLException;

    ColumnMetadata getKeys(String table) throws SQLException;

    ColumnStatistic getColumnStatistic(String sql) throws SQLException;

    List<String> getSchemas() throws SQLException;

    List<TableStatistic> getAllTableStatistic() throws SQLException;
}
