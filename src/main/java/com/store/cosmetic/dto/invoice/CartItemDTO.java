package com.store.cosmetic.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CartItemDTO {
    private Double totalPrice;
    private Long totalQuantity;
    private List<InvoiceItemDTO> items;

    public CartItemDTO(Double totalPrice, Long totalQuantity, List<InvoiceItemDTO> items) {
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.items = items;
    }
}
