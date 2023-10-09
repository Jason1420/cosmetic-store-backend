package com.store.cosmetic.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoiceItem")
@Data
@NoArgsConstructor
public class InvoiceItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "invoiceNumber", referencedColumnName = "id")
    private InvoiceEntity invoiceNumber;
    @ManyToOne
    @JoinColumn(name = "itemNumber", referencedColumnName = "id")
    private ItemEntity item;
    private Long quantity;
}
