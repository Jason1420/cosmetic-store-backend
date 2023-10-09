package com.store.cosmetic.repository;

import com.store.cosmetic.entity.InvoiceItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItemEntity, Long> {
    InvoiceItemEntity findOneById(Long id);

    List<InvoiceItemEntity> findAll();

}
