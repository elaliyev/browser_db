package com.db.browser.dbbrowser.db;

import com.db.browser.dbbrowser.exception.ConnectionException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class DBConnection {

    private static DBConnection INSTANCE;
    private Connection connection;


    private DBConnection(com.db.browser.dbbrowser.model.Connection connectionDetails) throws SQLException {
            this.connection = Utils.getDataSource(connectionDetails).getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() {
        if (INSTANCE == null) {
            log.error("You should first call init method");
            throw new ConnectionException("You are not connected");
        }

        return INSTANCE;
    }

    public synchronized static DBConnection init(com.db.browser.dbbrowser.model.Connection connectionDetails) throws SQLException {
        if (INSTANCE == null)
        {
            INSTANCE = new DBConnection(connectionDetails);
        }
        return INSTANCE;
    }
}
