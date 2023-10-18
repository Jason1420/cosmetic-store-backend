package com.store.cosmetic.converter;

import com.store.cosmetic.dto.invoice.InvoiceDTO;
import com.store.cosmetic.entity.invoice.Invoice;
import com.store.cosmetic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class InvoiceConverter {
    private static final String PREFIX = "INV-";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final AtomicInteger invoiceCounter = new AtomicInteger(1);

    private final CartItemConverter cartItemConverter;
    private final UserRepository userRepository;

    public static String generateInvoiceCode() {
        String currentDate = DATE_FORMAT.format(new Date());

        int uniqueNumber = invoiceCounter.getAndIncrement();

        String formattedNumber = String.format("%05d", uniqueNumber);

        String invoiceCode = PREFIX + currentDate + "-" + formattedNumber;
        return invoiceCode;
    }

    public Invoice toEntity(InvoiceDTO invoiceDTO) {
        return new Invoice(
                generateInvoiceCode(),
                invoiceDTO.getCustomerName(),
                invoiceDTO.getCustomerEmail(),
                invoiceDTO.getCustomerPhoneNumber(),
                invoiceDTO.getCustomerAddress(),
                true,
                cartItemConverter.toEntity(invoiceDTO.getCartItem()),
                userRepository.findOneByUsername(invoiceDTO.getCustomerUsername()),
                invoiceDTO.getInvoiceStatus()
        );
    }

    public InvoiceDTO toDTO(Invoice invoiceEntity) {
        return new InvoiceDTO(
                invoiceEntity.getId(),
                invoiceEntity.getCode(),
                invoiceEntity.getCustomerName(),
                invoiceEntity.getCustomerEmail(),
                invoiceEntity.getCustomerPhoneNumber(),
                invoiceEntity.getCustomerAddress(),
                true,
                cartItemConverter.toDTO(invoiceEntity.getCartItem()),
                invoiceEntity.getCustomer() != null ? invoiceEntity.getCustomer().getUsername() : null,
                invoiceEntity.getInvoiceStatus());

    }
}
