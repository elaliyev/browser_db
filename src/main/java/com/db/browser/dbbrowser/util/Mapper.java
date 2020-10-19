package com.db.browser.dbbrowser.util;

import com.db.browser.dbbrowser.model.Connection;
import com.db.browser.dbbrowser.model.Vendor;

public class Mapper {

    public static Connection mapConnection(ConnectionRequest connectionRequest, Vendor vendor) {
        Connection connection = new Connection();
        connection.setPassword(connectionRequest.getPassword());
        connection.setUsername(connectionRequest.getUsername());
        connection.setPort(connectionRequest.getPort());
        connection.setName(connectionRequest.getName());
        connection.setHostname(connectionRequest.getHostname());
        connection.setDbName(connectionRequest.getDbName());
        connection.setVendor(vendor);
        connection.setUrl(generateUrl(vendor, connectionRequest));
        return connection;
    }

    private static String generateUrl(Vendor vendor, ConnectionRequest connectionRequest) {
        StringBuilder connectionUrl = new StringBuilder("jdbc:");
        connectionUrl.append(vendor.getName())
                .append("://")
                .append(connectionRequest.getHostname())
                .append(":")
                .append(connectionRequest.getPort())
                .append("/")
                .append(connectionRequest.getDbName());
        return connectionUrl.toString();
    }
}
