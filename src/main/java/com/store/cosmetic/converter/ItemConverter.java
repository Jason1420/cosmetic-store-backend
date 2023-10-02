package com.store.cosmetic.converter;

import com.store.cosmetic.dto.ItemDTO;
import com.store.cosmetic.dto.NewItemDTO;
import com.store.cosmetic.entity.BrandEntity;
import com.store.cosmetic.entity.ItemEntity;
import com.store.cosmetic.entity.StatusEntity;
import com.store.cosmetic.entity.TypeEntity;
import com.store.cosmetic.repository.BrandRepository;
import com.store.cosmetic.repository.StatusRepository;
import com.store.cosmetic.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class ItemConverter {
    private final BrandRepository brandRepository;
    private final TypeRepository typeRepository;
    private final StatusRepository statusRepository;

    public ItemDTO toDTO(ItemEntity itemEntity) {
        return new ItemDTO(itemEntity.getId(),
                itemEntity.getName(),
                itemEntity.getBrand().getName(),
                itemEntity.getType().getName(),
                itemEntity.getPrice(),
                changeBlobToUrlData(itemEntity.getImage(), itemEntity.getMimeData()),
                itemEntity.getDescription(),
                itemEntity.getStatus().getName());
    }

    public ItemEntity toEntity(ItemDTO itemDTO) {
        TypeEntity type = typeRepository.findOneByName(itemDTO.getType());
        BrandEntity brand = brandRepository.findOneByName(itemDTO.getBrand());
        StatusEntity status = statusRepository.findOneByName(itemDTO.getStatus());

        return new ItemEntity(itemDTO.getId(),
                itemDTO.getName(),
                brand,
                type,
                itemDTO.getPrice(),
                changeURLdataToBlob(itemDTO.getImage()),
                itemDTO.getDescription(),
                status
        );
    }

    public ItemEntity newItemToEntity(NewItemDTO newItemDTO) {
        TypeEntity type = typeRepository.findOneByName(newItemDTO.getType());
        BrandEntity brand = brandRepository.findOneByName(newItemDTO.getBrand());
        StatusEntity status = statusRepository.findOneByName(newItemDTO.getStatus());
        return new ItemEntity(newItemDTO.getName(),
                brand,
                type,
                newItemDTO.getPrice(),
                changeURLdataToBlob(newItemDTO.getImage()),
                getMIMEData(newItemDTO.getImage()),
                newItemDTO.getDescription(),
                status);
    }

    public byte[] changeURLdataToBlob(String urlData) {
        if (urlData != null) {
            String base64Data = urlData.split(",")[1];
            byte[] binaryData = Base64.getDecoder().decode(base64Data);
            return binaryData;
        }
        return null;
    }

    public String getMIMEData(String urlData) {
        int headerEndIndex = urlData.indexOf(";");
        if (headerEndIndex != -1) {
            String header = urlData.substring(5, headerEndIndex);
            return header;
        }
        return null;
    }

    public String changeBlobToUrlData(byte[] blobData, String mimeData) {
        if (blobData != null && mimeData != null) {
            String base64Data = Base64.getEncoder().encodeToString(blobData);
            String dataUrl = "data:" + mimeData + ";base64," + base64Data;
            return dataUrl;
        }
        return null;
    }


}
