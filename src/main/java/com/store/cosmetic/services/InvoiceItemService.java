package com.store.cosmetic.services;

import com.store.cosmetic.entity.invoice.InvoiceItem;
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

    public Page<InvoiceItem> showAllInvoiceItemPagination(int offset, int size) {
        return invoiceItemRepository.findAll(PageRequest.of(offset - 1, size));
    }

    public List<InvoiceItem> getAllInvoiceItem() {
        return invoiceItemRepository.findAll();
    }

    public void addNew(InvoiceItem newInvoiceItem) {
        invoiceItemRepository.save(newInvoiceItem);
    }

    public void update(Long id, InvoiceItem invoiceItemEntity) {
        InvoiceItem invoiceItemOld = invoiceItemRepository.findOneById(id);
        invoiceItemEntity.setId(invoiceItemOld.getId());
        invoiceItemRepository.save(invoiceItemEntity);
    }
}
