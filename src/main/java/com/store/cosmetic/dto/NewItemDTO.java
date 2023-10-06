package com.store.cosmetic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewItemDTO {
    private String name;
    private String brand;
    private String type;
    private String status;
    private Double price;
    private String image;
    private String description;
}
