package com.db.browser.dbbrowser.service;

import com.db.browser.dbbrowser.model.Connection;
import com.db.browser.dbbrowser.repository.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnectionServiceImpl implements ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Override
    public Connection save(Connection connection) {
        return connectionRepository.save(connection);
    }

    @Override
    public void remove(Connection connection) {
        connectionRepository.delete(connection);
    }

    @Override
    public List<Connection> getAllConnections() {
        return connectionRepository.findAll();
    }

    @Override
    public Optional<Connection> getConnectionById(long id) {
        return connectionRepository.findById(id);
    }

    @Override
    public Optional<Connection> getConnectionByName(String name) {
        return connectionRepository.findByName(name);
    }

    @Override
    public List<Connection> getConnectionsByVendorNameAndVendorVersion(String name, String version) {
        return connectionRepository.findConnectionsByVendorNameAndVendorVersion(name, version);
    }
}
