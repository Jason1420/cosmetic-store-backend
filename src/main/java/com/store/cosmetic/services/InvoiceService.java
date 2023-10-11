package com.store.cosmetic.services;

import com.store.cosmetic.converter.InvoiceConverter;
import com.store.cosmetic.dto.invoice.InvoiceDTO;
import com.store.cosmetic.email.EmailServiceImp;
import com.store.cosmetic.entity.invoice.Invoice;
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
    private final InvoiceConverter invoiceConverter;
    private final EmailServiceImp emailService;

    public Page<Invoice> showAllInvoicePagination(int offset, int size) {
        return invoiceRepository.findAll(PageRequest.of(offset - 1, size));
    }

    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    public void addNew(Invoice newInvoice) {
        invoiceRepository.save(newInvoice);
    }

    public void update(Long id, Invoice invoiceEntity) {
        Invoice invoiceOld = invoiceRepository.findOneById(id);
        invoiceEntity.setId(invoiceOld.getId());
        invoiceRepository.save(invoiceEntity);
    }

    public InvoiceDTO payment(InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceConverter.toEntity(invoiceDTO);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        InvoiceDTO returnDTO = invoiceConverter.toDTO(savedInvoice);
        emailService.generateEmail(savedInvoice, "abcs");
        return returnDTO;

    }
}
