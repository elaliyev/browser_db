package com.db.browser.dbbrowser.service;

import com.db.browser.dbbrowser.db.dao.TableDataDao;
import com.db.browser.dbbrowser.db.model.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static com.db.browser.dbbrowser.db.Constants.*;
import static com.db.browser.dbbrowser.db.Constants.SPACE;
import static com.db.browser.dbbrowser.db.Utils.applyAggregatorFunctions;

@Service
public class TableDataServiceImpl implements TableDataService {

    final TableDataDao tableDataDao;

    public TableDataServiceImpl(TableDataDao tableDataDao) {
        this.tableDataDao = tableDataDao;
    }

    @Override
    public List<String> getTables() throws SQLException {
        return tableDataDao.getTables();
    }

    @Override
    public List<RowData> getTableData(String table, List<Column> columns) throws Exception {
        return tableDataDao.getTableData(table, columns);
    }

    @Override
    public List<Column> getColumnsFromTable(String table) throws SQLException {
        return tableDataDao.getColumnsFromTable(table);
    }

    @Override
    public ColumnMetadata getKeys(String table) throws SQLException {
        return tableDataDao.getKeys(table);
    }

    @Override
    public ColumnStatistic getColumnStatistic(String table, String column) throws SQLException {

        StringBuilder sql = new StringBuilder(SELECT);
        sql.append(SPACE)
                .append(applyAggregatorFunctions(column))
                .append(SPACE)
                .append(FROM)
                .append(SPACE)
                .append(table);

        return tableDataDao.getColumnStatistic(sql.toString());
    }

    @Override
    public List<TableStatistic> getAllTableStatistic() throws Exception {

        List<TableStatistic> data = tableDataDao.getAllTableStatistic();
        return data;
    }

    @Override
    public List<String> getSchemas() throws SQLException {
        return tableDataDao.getSchemas();
    }
}
