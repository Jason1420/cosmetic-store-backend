package com.store.cosmetic.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;


@Entity
@Table(name = "item")
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
    @Column(columnDefinition = "BLOB")
    @Lob
    private byte[] image;
    private String mimeData;
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "name")
    private StatusEntity status;

    public ItemEntity(Long id, String name, BrandEntity brand, TypeEntity type,
                      Double price, byte[] image, String description, StatusEntity status) {
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
                      Double price, byte[] image, String mimeData, String description,
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
