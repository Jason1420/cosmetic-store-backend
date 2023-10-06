package com.store.cosmetic.controller;

import com.store.cosmetic.dto.DefaultDataDTO;
import com.store.cosmetic.dto.ItemDTO;
import com.store.cosmetic.dto.NewItemDTO;
import com.store.cosmetic.help.Result;
import com.store.cosmetic.help.StatusCode;
import com.store.cosmetic.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ItemAPI {

    /**
     * Declare Bean
     **/
    private final ItemService itemService;


    /**
     * Get method
     **/
    @GetMapping("/getAll")
    private Result getAllItems() {
        List<ItemDTO> listAllItem = itemService.getAllItems();
        return new Result(true, StatusCode.SUCCESS, "Get all items success", listAllItem);
    }

    @GetMapping("/getDefaultData")
    private Result getDefaultData() {
        DefaultDataDTO defaultData = itemService.getDefaultData();
        return new Result(true, StatusCode.SUCCESS, "Get default data success", defaultData);
    }

    @GetMapping("/getOneItem/{id}")
    private Result getOneItem(@PathVariable("id") Long id) {
        ItemDTO detailItem = itemService.getItemById(id);
        return new Result(true, StatusCode.SUCCESS, "Get one item success", detailItem);
    }

//    @GetMapping("/search")
//    private Result searchItemByBrand(@RequestParam("brands") List<String> brands){
//        List<ItemDTO> listItem = itemService.searchByBrand(brands);
//        return new Result(true, StatusCode.SUCCESS, "Search by brand success",listItem);
//    }

    @GetMapping("/search")
    private Result searchItemByBrandAndTypePagination(@RequestParam("typeId") Long typeId,
                                            @RequestParam(value = "brands", required = false) List<String> brands,
                                            @RequestParam("page") int page) {
        List<ItemDTO> listItem = itemService.searchByTypeAndBrand(typeId, brands,page);
        return new Result(true, StatusCode.SUCCESS, "Search by type and brand success", listItem);
    }

    @GetMapping("/count-item-by-typeid-brands")
    private Result countItemByBrandAndTypePagination(@RequestParam("typeId") Long typeId,
                                                      @RequestParam(value = "brands", required = false) List<String> brands) {
       Long quantity = itemService.countItemByBrandAndTypePagination(typeId, brands);
        return new Result(true, StatusCode.SUCCESS, "Count success", quantity);
    }

    @GetMapping("/count-item-by-typeid")
    private Result countItemByTypeName(@RequestParam("typeId") Long typeId) {
        Long quantity = itemService.countItemByTypeName(typeId);
        return new Result(true, StatusCode.SUCCESS, "Count success", quantity);
    }

    @GetMapping("/search/{typeId}")
    private Result searchItemByTypePagination(@PathVariable("typeId") Long typeId,
                                    @RequestParam("page") int page) {
        List<ItemDTO> listItem = itemService.searchByTypeId(typeId,page);
        return new Result(true, StatusCode.SUCCESS, "Search by type success", listItem);
    }




    @GetMapping("/hot-deal")
    private Result searchHotDealItem(@RequestParam("page") int page) {
        List<ItemDTO> listItem = itemService.searchHotDealItem(page);
        return new Result(true, StatusCode.SUCCESS, "Search hot-deal items success", listItem);
    }

    @GetMapping("/all-hot-item")
    private Result findAllHotDealItem() {
        List<ItemDTO> listItem = itemService.findAllHotDealItem();
        return new Result(true, StatusCode.SUCCESS, "Find all hot-deal items success", listItem);
    }

    @GetMapping("/new-item")
    private Result searchNewItem(@RequestParam("page") int page) {
        List<ItemDTO> listItem = itemService.searchNewItem(page);
        return new Result(true, StatusCode.SUCCESS, "Search new items success", listItem);
    }

    @GetMapping("/all-new-item")
    private Result findAllNewItem() {
        List<ItemDTO> listItem = itemService.findAllNewItem();
        return new Result(true, StatusCode.SUCCESS, "Find all new items success", listItem);
    }

    @GetMapping("/best-sellers")
    private Result searchBestSellers(@RequestParam("page") int page) {
        List<ItemDTO> listItem = itemService.searchBestSellers(page);
        return new Result(true, StatusCode.SUCCESS, "Search best sellers success", listItem);
    }

    @GetMapping("/all-best-sellers")
    private Result findAllBestSellers() {
        List<ItemDTO> listItem = itemService.findAllBestSellers();
        return new Result(true, StatusCode.SUCCESS, "Find all best sellers success", listItem);
    }
    @GetMapping("/gift")
    private Result searchGift(@RequestParam("page") int page) {
        List<ItemDTO> listItem = itemService.searchGift(page);
        return new Result(true, StatusCode.SUCCESS, "Search best sellers success", listItem);
    }

    @GetMapping("/all-gift")
    private Result findAllGift() {
        List<ItemDTO> listItem = itemService.findAllGift();
        return new Result(true, StatusCode.SUCCESS, "Find all best sellers success", listItem);
    }

    /**
     * Post method
     **/
    @PostMapping("/item")
    private Result addNewItem(@RequestBody NewItemDTO newItemDTO) {
        ItemDTO savedItem = itemService.addNewItem(newItemDTO);
        return new Result(true, StatusCode.SUCCESS, "Add items success", savedItem);
    }


}
