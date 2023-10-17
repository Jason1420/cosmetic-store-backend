package com.store.cosmetic.controller;

import com.store.cosmetic.dto.invoice.InvoiceDTO;
import com.store.cosmetic.email.EmailServiceImp;
import com.store.cosmetic.exception.helper.Result;
import com.store.cosmetic.exception.helper.StatusCode;
import com.store.cosmetic.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class InvoiceAPI {
    private final InvoiceService invoiceService;
    private final EmailServiceImp emailServiceImp;

    @PostMapping("/payment")
    private Result payment(@RequestBody InvoiceDTO invoiceDTO) {
        InvoiceDTO savedInvoice = invoiceService.payment(invoiceDTO);
        return new Result(true, StatusCode.SUCCESS, "Payment success", savedInvoice);
    }

    @GetMapping("/getSomeInvoice/{id}")
    private Result getSomeInvoice(@PathVariable("id") Long id) {
        List<InvoiceDTO> listInvoice = invoiceService.getSomeInvoice(id);
        return new Result(true, StatusCode.SUCCESS, "Get invoices success", listInvoice);
    }

    @GetMapping("/invoice/{code}")
    private Result searchInvoiceByCode(@PathVariable("code") String code) {
        InvoiceDTO searchedInvoice = invoiceService.searchInvoiceByCode(code);
        return new Result(true, StatusCode.SUCCESS, "Search invoices success", searchedInvoice);
    }

    @PutMapping("/invoice/{code}")
    private Result cancelInvoiceByCode(@PathVariable("code") String code) {
        InvoiceDTO searchedInvoice = invoiceService.cancelInvoiceByCode(code);
        return new Result(true, StatusCode.SUCCESS, "Cancel invoices success", searchedInvoice);
    }

    @GetMapping("/testMail")
    private Result testMail() {
        emailServiceImp.sendSimpleEmail("blqckhol3@gmail.com",
                "This is email body",
                "This is email subject");
        return new Result(true, StatusCode.SUCCESS, "send mail success");
    }
}
