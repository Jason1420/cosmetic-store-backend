package com.store.cosmetic.services;

import com.store.cosmetic.converter.ItemConverter;
import com.store.cosmetic.dto.DefaultDataDTO;
import com.store.cosmetic.dto.ItemDTO;
import com.store.cosmetic.dto.NewItemDTO;
import com.store.cosmetic.entity.BrandEntity;
import com.store.cosmetic.entity.ItemEntity;
import com.store.cosmetic.entity.StatusEntity;
import com.store.cosmetic.entity.TypeEntity;
import com.store.cosmetic.help.ItemBrand;
import com.store.cosmetic.help.ItemType;
import com.store.cosmetic.help.Result;
import com.store.cosmetic.help.StatusCode;
import com.store.cosmetic.repository.BrandRepository;
import com.store.cosmetic.repository.ItemRepository;
import com.store.cosmetic.repository.StatusRepository;
import com.store.cosmetic.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemConverter itemConverter;
    private final BrandRepository brandRepository;
    private final TypeRepository typeRepository;
    private final StatusRepository statusRepository;
    public List<ItemDTO> getAllItems() {
        List<ItemEntity> listEntity = itemRepository.findAll();
        List<ItemDTO> listDTO = listEntity.stream().map(itemConverter::toDTO).collect(Collectors.toList());
        return listDTO;
    }

    public ItemDTO addNewItem(NewItemDTO newItemDTO) {
        ItemEntity itemEntity = itemConverter.newItemToEntity(newItemDTO);
        ItemEntity savedItem = itemRepository.save(itemEntity);

        ItemDTO returnDTO = itemConverter.toDTO(savedItem);
        return returnDTO;
    }

    public DefaultDataDTO getDefaultData() {
        List<BrandEntity> listBrand = brandRepository.findAll();
        List<TypeEntity> listType = typeRepository.findAll();
        List<StatusEntity> listStatus = statusRepository.findAll();
        return new DefaultDataDTO(listBrand,listType,listStatus);
    }


    public ItemDTO getItemById(Long id) {
        ItemEntity itemEntity = itemRepository.findOneById(id);
        ItemDTO itemDTO = itemConverter.toDTO(itemEntity);
        return itemDTO;
    }
}
