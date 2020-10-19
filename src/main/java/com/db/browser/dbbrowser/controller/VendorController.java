package com.db.browser.dbbrowser.controller;

import com.db.browser.dbbrowser.exception.NotFoundException;
import com.db.browser.dbbrowser.model.Connection;
import com.db.browser.dbbrowser.model.Vendor;
import com.db.browser.dbbrowser.service.VendorService;
import com.db.browser.dbbrowser.util.ConnectionRequest;
import com.db.browser.dbbrowser.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/vendor",produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vendor> saveConnection(@RequestBody Vendor vendor) throws Exception {

        Vendor savedVendor = vendorService.save(vendor);
        log.info("Vendor saved {}", savedVendor);
        return new ResponseEntity<>(savedVendor, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vendor>> getAllConnections() {

        List<Vendor> vendors = vendorService.getAllVendors();
        log.info("all vendor. size: {}", vendors.size());
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable long id) {
        log.info("getVendorById method called. id: {}", id);
        return new ResponseEntity<>(vendorService.getVendorById(id)
                .orElseThrow(() -> new NotFoundException("ID", id)), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Vendor> updateVendor(@RequestBody Vendor vendor, @PathVariable long id) {

        log.info("updateVendor called. id:{}, vendor:{}", id, vendor);
        Optional<Vendor> optionalVendor = vendorService.getVendorById(id);

        if (!optionalVendor.isPresent())
            return ResponseEntity.notFound().build();

        Vendor existingVendor = optionalVendor.get();
        existingVendor.setName(vendor.getName());
        existingVendor.setVersion(vendor.getVersion());

        vendorService.save(existingVendor);

        return new ResponseEntity<>(existingVendor, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteVendor(@PathVariable long id) {

        log.info("deleteVendor is called. id:{}", id);
        Vendor vendor = vendorService.getVendorById(id)
                .orElseThrow(() -> new NotFoundException("ID", id));

        vendorService.remove(vendor);
        return ResponseEntity.ok().build();
    }

}
