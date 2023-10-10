package com.store.cosmetic.repository;

import com.store.cosmetic.entity.invoice.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
    InvoiceItem findOneById(Long id);

    List<InvoiceItem> findAll();

}
