package com.store.cosmetic.converter;

import com.store.cosmetic.dto.invoice.InvoiceItemDTO;
import com.store.cosmetic.entity.invoice.InvoiceItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceItemConverter {
    private final ItemConverter itemConverter;
    public InvoiceItem toEntity(InvoiceItemDTO invoiceItemDTO){
        return new InvoiceItem(itemConverter.toEntity(invoiceItemDTO.getItem())
                ,invoiceItemDTO.getQuantity());
    }
    public InvoiceItemDTO toDTO(InvoiceItem invoiceItemEntity){
        return new InvoiceItemDTO(itemConverter.toDTO(invoiceItemEntity.getItem())
                ,invoiceItemEntity.getQuantity());
    }
}
