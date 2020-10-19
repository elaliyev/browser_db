package com.db.browser.dbbrowser.repository;

import com.db.browser.dbbrowser.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
