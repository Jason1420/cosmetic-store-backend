package com.store.cosmetic.services;

import com.store.cosmetic.converter.ItemConverter;
import com.store.cosmetic.dto.DefaultDataDTO;
import com.store.cosmetic.dto.ItemDTO;
import com.store.cosmetic.dto.NewItemDTO;
import com.store.cosmetic.entity.BrandEntity;
import com.store.cosmetic.entity.ItemEntity;
import com.store.cosmetic.entity.StatusEntity;
import com.store.cosmetic.entity.TypeEntity;
import com.store.cosmetic.help.Constant;
import com.store.cosmetic.repository.BrandRepository;
import com.store.cosmetic.repository.ItemRepository;
import com.store.cosmetic.repository.StatusRepository;
import com.store.cosmetic.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return new DefaultDataDTO(listBrand, listType, listStatus);
    }


    public ItemDTO getItemById(Long id) {
        ItemEntity itemEntity = itemRepository.findOneById(id);
        ItemDTO itemDTO = itemConverter.toDTO(itemEntity);
        return itemDTO;
    }

    public List<ItemDTO> searchByTypeAndBrand(Long typeId, List<String> brands,int page) {
        List<ItemEntity> listItem = itemRepository.findByTypeAndBrandContainingIgnoreCase
                (typeId, brands,PageRequest.of(page - 1, Constant.CLASSIFY_SIZE_PAGE));
        List<ItemDTO> returnDTO = listItem.stream().map(itemConverter::toDTO).collect(Collectors.toList());
        ;
        return returnDTO;
    }

    public List<ItemDTO> searchByTypeId(Long typeId,int page) {
        List<ItemEntity> listItem = itemRepository.findAllByTypeId
                (typeId,PageRequest.of(page - 1, Constant.CLASSIFY_SIZE_PAGE));
        List<ItemDTO> returnDTO = listItem.stream().map(itemConverter::toDTO).collect(Collectors.toList());

        return returnDTO;
    }

    public List<ItemDTO> searchHotDealItem(int page) {
        Page<ItemEntity> paginationEntities = itemRepository.findByStatusHot(PageRequest.of(page - 1, Constant.SIZE_PAGE));
        List<ItemDTO> returnDTO = paginationEntities.stream().map(itemConverter::toDTO).collect(Collectors.toList());
        return returnDTO;
    }

    public List<ItemDTO> findAllHotDealItem() {
        List<ItemEntity> listItem = itemRepository.findAllHotDealItem();
        List<ItemDTO> returnDTO = listItem.stream().map(itemConverter::toDTO).collect(Collectors.toList());
        ;
        return returnDTO;
    }

    public List<ItemDTO> searchNewItem(int page) {
        Page<ItemEntity> paginationEntities = itemRepository.findByStatusNew(PageRequest.of(page - 1, Constant.SIZE_PAGE));
        List<ItemDTO> returnDTO = paginationEntities.stream().map(itemConverter::toDTO).collect(Collectors.toList());
        return returnDTO;
    }

    public List<ItemDTO> findAllNewItem() {
        List<ItemEntity> listItem = itemRepository.findAllNewItem();
        List<ItemDTO> returnDTO = listItem.stream().map(itemConverter::toDTO).collect(Collectors.toList());
        ;
        return returnDTO;
    }

    public List<ItemDTO> searchBestSellers(int page) {
        Page<ItemEntity> paginationEntities = itemRepository.searchBestSellers(PageRequest.of(page - 1, Constant.SIZE_PAGE));
        List<ItemDTO> returnDTO = paginationEntities.stream().map(itemConverter::toDTO).collect(Collectors.toList());
        return returnDTO;
    }

    public List<ItemDTO> findAllBestSellers() {
        List<ItemEntity> listItem = itemRepository.findAllBestSellers();
        List<ItemDTO> returnDTO = listItem.stream().map(itemConverter::toDTO).collect(Collectors.toList());
        ;
        return returnDTO;
    }

    public List<ItemDTO> searchGift(int page) {
        Page<ItemEntity> paginationEntities = itemRepository.searchGift(PageRequest.of(page - 1, Constant.SIZE_PAGE));
        List<ItemDTO> returnDTO = paginationEntities.stream().map(itemConverter::toDTO).collect(Collectors.toList());
        return returnDTO;
    }

    public List<ItemDTO> findAllGift() {
        List<ItemEntity> listItem = itemRepository.findAllGift();
        List<ItemDTO> returnDTO = listItem.stream().map(itemConverter::toDTO).collect(Collectors.toList());
        ;
        return returnDTO;
    }

    public Long countItemByTypeName(Long typeId) {
        Long quantity = itemRepository.countAllByType_Id(typeId);
        return quantity;
    }

    public Long countItemByBrandAndTypePagination(Long typeId, List<String> brands) {
        Long quantity = itemRepository.countItemByBrandAndTypePagination(typeId, brands);
        return quantity;
    }
}
