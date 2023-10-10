package com.store.cosmetic.repository;

import com.store.cosmetic.entity.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findOneById(Long id);

    List<Invoice> findAll();

}
