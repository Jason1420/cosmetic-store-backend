package com.store.cosmetic.dto;

import com.store.cosmetic.help.ItemBrand;
import com.store.cosmetic.help.ItemType;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String name;
    private String brand;
    private String type;
    private Double price;
    private String image;
    private String description;
    private String status;


}
