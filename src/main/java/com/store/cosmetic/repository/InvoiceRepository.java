package com.store.cosmetic.repository;

import com.store.cosmetic.entity.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findOneById(Long id);

    List<Invoice> findAll();


    List<Invoice> findByCustomerEmail(String mail);

    @Query("select i from Invoice i where i.customer.id = :id ")
    List<Invoice> findByCustomerId(Long id);

    List<Invoice> findOneByCode(String code);
}
