package com.db.browser.dbbrowser.service;

import com.db.browser.dbbrowser.model.Connection;
import com.db.browser.dbbrowser.model.Vendor;

import java.util.List;
import java.util.Optional;

public interface ConnectionService {

    Connection save(Connection connection);

    void remove(Connection connection);

    List<Connection> getAllConnections();

    Optional<Connection> getConnectionById(long id);

    Optional<Connection> getConnectionByName(String name);

    List<Connection> getConnectionsByVendorNameAndVendorVersion(String name, String version);

}
