package com.db.browser.dbbrowser.service;

import com.db.browser.dbbrowser.model.Vendor;
import com.db.browser.dbbrowser.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService{

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Optional<Vendor> getVendorById(long id) {
        return vendorRepository.findById(id);
    }

    @Override
    public Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public void remove(Vendor vendor) {
        vendorRepository.delete(vendor);
    }

}
