package com.db.browser.dbbrowser.repository;

import com.db.browser.dbbrowser.model.Connection;
import com.db.browser.dbbrowser.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    List<Connection> findAllByVendor(Vendor vendor);

    Optional<Connection> findByName(String name);

    @Query("SELECT c FROM Connection c WHERE c.vendor.name=:name AND c.vendor.version=:version")
    List<Connection> findConnectionsByVendorNameAndVendorVersion(String name, String version);
}
