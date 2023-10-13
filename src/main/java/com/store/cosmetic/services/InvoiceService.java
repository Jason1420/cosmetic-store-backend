package com.store.cosmetic.services;

import com.store.cosmetic.converter.InvoiceConverter;
import com.store.cosmetic.dto.invoice.InvoiceDTO;
import com.store.cosmetic.email.EmailServiceImp;
import com.store.cosmetic.entity.invoice.Invoice;
import com.store.cosmetic.entity.invoice.InvoiceStatus;
import com.store.cosmetic.repository.InvoiceItemRepository;
import com.store.cosmetic.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceConverter invoiceConverter;
    private final EmailServiceImp emailService;
    private final InvoiceItemRepository invoiceItemRepository;

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
        if (savedInvoice != null) {
            invoice.getCartItem().getItems().stream()
                    .map(invoiceItem -> {
                        invoiceItem.setCartItem(savedInvoice.getCartItem());
                        return invoiceItem;
                    })
                    .map(invoiceItemRepository::save).collect(Collectors.toList());
        }
        InvoiceDTO returnDTO = invoiceConverter.toDTO(savedInvoice);
        emailService.generateEmail(savedInvoice, "abcs");
        return returnDTO;

    }

    public List<InvoiceDTO> getSomeInvoice(Long id) {
        List<Invoice> listInvoice = invoiceRepository.findByCustomerId(id);
        List<InvoiceDTO> returnListDTO = listInvoice.stream().map(invoiceConverter::toDTO).collect(Collectors.toList());
        return returnListDTO;
    }

    public InvoiceDTO searchInvoiceByCode(String code) {
        List<Invoice> searchedInvoice = invoiceRepository.findOneByCode(code);
        InvoiceDTO returnDTO = searchedInvoice.stream().
                map(invoiceConverter::toDTO).collect(Collectors.toList()).get(0);
        return returnDTO;

    }

    public InvoiceDTO cancelInvoiceByCode(String code) {
        List<Invoice> searchedInvoice = invoiceRepository.findOneByCode(code);
        List<Invoice> cancelInvoice = searchedInvoice.stream().map(item -> {
            item.setInvoiceStatus(InvoiceStatus.CANCELED);
            invoiceRepository.save(item);
            return item;
        }).collect(Collectors.toList());
        InvoiceDTO returnDTO = invoiceConverter.toDTO(cancelInvoice.get(0));
        return returnDTO;
    }
}
