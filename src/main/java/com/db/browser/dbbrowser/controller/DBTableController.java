package com.db.browser.dbbrowser.controller;

import com.db.browser.dbbrowser.db.model.Column;
import com.db.browser.dbbrowser.db.model.ColumnMetadata;
import com.db.browser.dbbrowser.db.model.RowData;
import com.db.browser.dbbrowser.service.TableDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.*;

@RestController
@RequestMapping(path = "/table",produces = MediaType.APPLICATION_JSON_VALUE)
public class DBTableController {

    private final TableDataService tableDataService;

    public DBTableController(TableDataService tableDataService) {
        this.tableDataService = tableDataService;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getTables() throws SQLException {

        List<String> tables = tableDataService.getTables();
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @GetMapping("/{table}/columns")
    public ResponseEntity<ColumnMetadata> getColumns(@PathVariable String table) throws SQLException {

        List<Column> columns = tableDataService.getColumnsFromTable(table);
        ColumnMetadata columnMetadata = tableDataService.getKeys(table);
        columnMetadata.setColumns(columns);
        return new ResponseEntity<>(columnMetadata, HttpStatus.OK);
    }

    @GetMapping("/{table}/data")
    public ResponseEntity<List<RowData>> GetTableData(@PathVariable String table) throws Exception {

        List<Column> columns = tableDataService.getColumnsFromTable(table);
        List<RowData> data = tableDataService.getTableData(table, columns);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
