package com.db.browser.dbbrowser.util;

import com.db.browser.dbbrowser.model.Vendor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionRequest {

    private String name;
    private String hostname;
    private int port;
    private String dbName;
    private String username;
    private String password;
    private long vendorId;
}
