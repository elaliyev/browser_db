package com.db.browser.dbbrowser.controller;

import com.db.browser.dbbrowser.db.DBConnection;
import com.db.browser.dbbrowser.exception.NotFoundException;
import com.db.browser.dbbrowser.model.Connection;
import com.db.browser.dbbrowser.service.ConnectionService;
import com.db.browser.dbbrowser.service.VendorService;
import com.db.browser.dbbrowser.util.ConnectionParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/connection",produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ConnectionController {

    private final ConnectionService connectionService;
    private final VendorService vendorService;

    public ConnectionController(ConnectionService connectionService, VendorService vendorService) {
        this.connectionService = connectionService;
        this.vendorService = vendorService;
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Connection> saveConnection(@RequestBody Connection connection) throws Exception {

        connection.generateUrl();

        Connection savedConnection = connectionService.save(connection);
        log.info("Connection saved {}", savedConnection);
        return new ResponseEntity<>(savedConnection, HttpStatus.CREATED);
    }

    @PostMapping(value = "/connect", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> connect(@RequestBody ConnectionParam connection) throws SQLException {

        Connection existingConnection = connectionService.getConnectionByName(connection.getName())
                .orElseThrow(()->new NotFoundException("NAME", connection.getName()));

        DBConnection.init(existingConnection);
        log.info("Connected to the {}",existingConnection.getDbName());
        return new ResponseEntity<>("Connected to "+existingConnection.getDbName(), HttpStatus.OK);
    }

    @GetMapping("/find-by-vendor/{name}/{version}")
    public ResponseEntity<List<Connection>> getConnectionsByVendorNameAndVersion(@PathVariable String name, @PathVariable String version) {

        List<Connection> connections = connectionService.getConnectionsByVendorNameAndVendorVersion(name, version);
        return new ResponseEntity<>(connections,HttpStatus.OK);
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<Connection> getConnectionsByName(@PathVariable String name) {

        log.info("getConnectionsByName method called. name: {}", name);
        return new ResponseEntity<>(connectionService.getConnectionByName(name)
                .orElseThrow(()->new NotFoundException("NAME", name)), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Connection> getConnectionsById(@PathVariable long id) {
        log.info("getConnectionsById method called. id: {}", id);
        return new ResponseEntity<>(connectionService.getConnectionById(id)
                .orElseThrow(() -> new NotFoundException("ID", id)), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Connection>> getAllConnections() {

        List<Connection> connections = connectionService.getAllConnections();
        log.info("all connections. size: {}", connections.size());
        return new ResponseEntity<>(connections,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Connection> updateConnection(@RequestBody Connection connection, @PathVariable long id) {

        log.info("updateConnection called. id:{}, connection:{}", id, connection);
        Optional<Connection> connectionOptional = connectionService.getConnectionById(id);

        if (!connectionOptional.isPresent())
            return ResponseEntity.notFound().build();

        Connection existingConnection = connectionOptional.get();
        existingConnection.setDbName(connection.getDbName());
        existingConnection.setHostname(connection.getHostname());
        existingConnection.setName(connection.getName());
        existingConnection.setPassword(connection.getPassword());
        existingConnection.setPort(connection.getPort());
        existingConnection.setUsername(connection.getUsername());
        existingConnection.setVendor(connection.getVendor());

        connectionService.save(existingConnection);

        return new ResponseEntity<>(existingConnection, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteConnection(@PathVariable long id) {

        log.info("deleteConnection is called. id:{}",id);
        Connection connection = connectionService.getConnectionById(id)
                .orElseThrow(() -> new NotFoundException("ID",id));

        connectionService.remove(connection);
        return ResponseEntity.ok().build();
    }
}
