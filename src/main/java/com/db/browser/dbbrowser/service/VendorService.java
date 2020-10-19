package com.db.browser.dbbrowser.service;

import com.db.browser.dbbrowser.model.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorService {

    List<Vendor> getAllVendors();
    Optional<Vendor> getVendorById(long id);
    Vendor save(Vendor vendor);
    void remove(Vendor vendor);
}
