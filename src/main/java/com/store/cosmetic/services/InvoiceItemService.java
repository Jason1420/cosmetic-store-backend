package com.store.cosmetic.services;

import com.store.cosmetic.entity.InvoiceItemEntity;
import com.store.cosmetic.repository.InvoiceItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class InvoiceItemService {
    private final InvoiceItemRepository invoiceItemRepository;

    public Page<InvoiceItemEntity> showAllInvoiceItemPagination(int offset, int size) {
        return invoiceItemRepository.findAll(PageRequest.of(offset - 1, size));
    }

    public List<InvoiceItemEntity> getAllInvoiceItem() {
        return invoiceItemRepository.findAll();
    }

    public void addNew(InvoiceItemEntity newInvoiceItemEntity) {
        invoiceItemRepository.save(newInvoiceItemEntity);
    }

    public void update(Long id, InvoiceItemEntity invoiceItemEntity) {
        InvoiceItemEntity departmentOld = invoiceItemRepository.findOneById(id);
        invoiceItemEntity.setId(departmentOld.getId());
        invoiceItemRepository.save(invoiceItemEntity);
    }
}
