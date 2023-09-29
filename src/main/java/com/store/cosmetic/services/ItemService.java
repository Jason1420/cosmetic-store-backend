package com.store.cosmetic.services;

import com.store.cosmetic.converter.ItemConverter;
import com.store.cosmetic.dto.ItemDTO;
import com.store.cosmetic.entity.ItemEntity;
import com.store.cosmetic.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemConverter itemConverter;
    public List<ItemDTO> getAllItems() {
        List<ItemEntity> listEntity = itemRepository.findAll();
        List<ItemDTO> listDTO = listEntity.stream().map(itemConverter::toDTO).collect(Collectors.toList());
        return listDTO;
    }

    public ItemDTO addNewItem(ItemDTO itemDTO) {
        ItemEntity itemEntity = itemConverter.toEntity(itemDTO);
        ItemEntity savedItem = itemRepository.save(itemEntity);

        ItemDTO returnDTO = itemConverter.toDTO(savedItem);

        return returnDTO;
    }
}
