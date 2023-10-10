package com.store.cosmetic.controller;

import com.store.cosmetic.dto.ItemDTO;
import com.store.cosmetic.dto.NewItemDTO;
import com.store.cosmetic.dto.invoice.InvoiceDTO;
import com.store.cosmetic.exception.helper.Result;
import com.store.cosmetic.exception.helper.StatusCode;
import com.store.cosmetic.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class InvoiceAPI {
    private final InvoiceService invoiceService;
    @PostMapping("/payment")
    private Result payment(@RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO savedInvoice = invoiceService.payment(invoiceDTO);
        return new Result(true, StatusCode.SUCCESS, "Transfer success", invoiceDTO);
    }
}
