package com.store.cosmetic.entity;

import com.store.cosmetic.help.ItemBrand;
import com.store.cosmetic.help.ItemType;
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
    private ItemBrand brand;
    private ItemType type;
    private Double price;
    @Lob
    private Blob image;
    private String description;

    public ItemEntity(Long id, String name, ItemBrand brand, ItemType type,
                      Double price, Blob image, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.image = image;
        this.description = description;
    }
}
