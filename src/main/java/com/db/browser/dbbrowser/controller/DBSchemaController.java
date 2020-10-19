package com.db.browser.dbbrowser.controller;

import com.db.browser.dbbrowser.service.TableDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/schema",produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class DBSchemaController {

    private final TableDataService tableDataService;

    public DBSchemaController(TableDataService tableDataService) {
        this.tableDataService = tableDataService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getSchemas() throws SQLException {

        List<String> schemas = tableDataService.getSchemas();
        return new ResponseEntity<>(schemas, HttpStatus.OK);
    }


}
