package com.store.cosmetic.entity.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.cosmetic.dto.invoice.InvoiceItemDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cartItem")
@Data
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;
    private Long totalQuantity;
    @OneToMany(mappedBy = "cartItem")
    @JsonIgnore
    private List<InvoiceItem> items;

    @OneToOne(mappedBy = "cartItem")
    private Invoice invoice;

    public CartItem(Double totalPrice, Long totalQuantity, List<InvoiceItem> items) {
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.items = items;
    }
}
