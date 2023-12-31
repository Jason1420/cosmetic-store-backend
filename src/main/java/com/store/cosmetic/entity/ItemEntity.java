package com.store.cosmetic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.cosmetic.entity.invoice.InvoiceItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "item",
        indexes = {@Index(name = "idx_item_status", columnList = "status"),
                @Index(name = "idx_item_type", columnList = "type")})
@Data
@NoArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "brand", referencedColumnName = "name")
    private BrandEntity brand;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "name")
    private TypeEntity type;


    private Double price;
    @Column(length = 10485760)
    private String image;
    private String mimeData;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "name")
    private StatusEntity status;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<InvoiceItem> items;

    public ItemEntity(Long id, String name, BrandEntity brand, TypeEntity type,
                      Double price, String image, String description, StatusEntity status) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.image = image;
        this.description = description;
        this.status = status;
    }

    public ItemEntity(String name, BrandEntity brand, TypeEntity type,
                      Double price, String image, String mimeData, String description,
                      StatusEntity status) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.image = image;
        this.mimeData = mimeData;
        this.description = description;
        this.status = status;
    }
}
