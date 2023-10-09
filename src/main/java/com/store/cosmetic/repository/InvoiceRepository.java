package com.store.cosmetic.repository;

import com.store.cosmetic.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    InvoiceEntity findOneById(Long id);

    List<InvoiceEntity> findAll();

}
