package com.store.cosmetic.entity.invoice;

import com.store.cosmetic.entity.ItemEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoiceItem")
@Data
@NoArgsConstructor
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item", referencedColumnName = "id")
    private ItemEntity item;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "cart_items", referencedColumnName = "id")
    private CartItem cartItem;

    public InvoiceItem(ItemEntity item, Long quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
