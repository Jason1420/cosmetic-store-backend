package com.store.cosmetic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
