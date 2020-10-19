package com.db.browser.dbbrowser.controller;

import com.db.browser.dbbrowser.db.model.ColumnStatistic;
import com.db.browser.dbbrowser.db.model.TableStatistic;
import com.db.browser.dbbrowser.service.TableDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.List;

@RestController
@RequestMapping(path = "/statistic",produces = MediaType.APPLICATION_JSON_VALUE)
public class DBStatisticController {

    private final TableDataService tableDataService;

    public DBStatisticController(TableDataService tableDataService) {
        this.tableDataService = tableDataService;
    }

    @GetMapping("/{table}/{column}")
    public ResponseEntity<ColumnStatistic> getColumnStatistic(@PathVariable String table, @PathVariable String column) throws SQLException {

        ColumnStatistic data = tableDataService.getColumnStatistic(table, column);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/all-table-statistic")
    public ResponseEntity<List<TableStatistic>> getAllTableStatistic() throws Exception {

        List<TableStatistic> data = tableDataService.getAllTableStatistic();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
