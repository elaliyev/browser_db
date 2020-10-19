package com.db.browser.dbbrowser.controller;

import com.db.browser.dbbrowser.model.Vendor;
import com.db.browser.dbbrowser.service.VendorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vendor",produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vendor>> getAllConnections() {

        List<Vendor> vendors = vendorService.getAllVendors();
        log.info("all vendor. size: {}", vendors.size());
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }


}
