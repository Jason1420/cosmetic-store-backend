package com.store.cosmetic.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvoiceDTO {
    private Long id;
    private String code;
    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;
    private String customerAddress;
    private Boolean status;
    private CartItemDTO cartItem;
    private String customerUsername;

    public InvoiceDTO(Long id, String code, String customerName, String customerEmail, String customerPhoneNumber,
                      String customerAddress, Boolean status, CartItemDTO cartItem,
                      String customerUsername) {
        this.id = id;
        this.code = code;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAddress = customerAddress;
        this.status = status;
        this.cartItem = cartItem;
        this.customerUsername = customerUsername;
    }
}
