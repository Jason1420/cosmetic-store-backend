package com.store.cosmetic.converter;

import com.store.cosmetic.dto.ItemDTO;
import com.store.cosmetic.entity.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter {
    public ItemDTO toDTO(ItemEntity itemEntity){
        return new ItemDTO(itemEntity.getId(),
                itemEntity.getName(),
                itemEntity.getBrand(),
                itemEntity.getType(),
                itemEntity.getPrice(),
                itemEntity.getImage(),
                itemEntity.getDescription());
    }
    public ItemEntity toEntity(ItemDTO itemDTO){
        return new ItemEntity(itemDTO.getId(),
                itemDTO.getName(),
                itemDTO.getBrand(),
                itemDTO.getType(),
                itemDTO.getPrice(),
                itemDTO.getImage(),
                itemDTO.getDescription());
    }
}
