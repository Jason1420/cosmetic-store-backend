package com.store.cosmetic.services;

import com.store.cosmetic.entity.InvoiceEntity;
import com.store.cosmetic.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public Page<InvoiceEntity> showAllInvoicePagination(int offset, int size) {
        return invoiceRepository.findAll(PageRequest.of(offset - 1, size));
    }

    public List<InvoiceEntity> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    public void addNew(InvoiceEntity newInvoiceEntity) {
        invoiceRepository.save(newInvoiceEntity);
    }

    public void update(Long id, InvoiceEntity invoiceEntity) {
        InvoiceEntity departmentOld = invoiceRepository.findOneById(id);
        invoiceEntity.setId(departmentOld.getId());
        invoiceRepository.save(invoiceEntity);
    }
}
