package com.store.cosmetic.controller;

import com.store.cosmetic.dto.BrandDTO;
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

    /** Declare Bean **/
    private final ItemService itemService;


    /** Get method **/
    @GetMapping("/getAll")
    private Result getAllItems(){
        List<ItemDTO> listAllItem = itemService.getAllItems();
        return new Result(true, StatusCode.SUCCESS, "Get all items success",listAllItem);
    }
    @GetMapping("/getDefaultData")
    private Result getDefaultData(){
        DefaultDataDTO defaultData = itemService.getDefaultData();
        return new Result(true, StatusCode.SUCCESS, "Get default data success",defaultData);
    }

    @GetMapping("/getOneItem/{id}")
    private Result getOneItem(@PathVariable("id") Long id){
        ItemDTO detailItem = itemService.getItemById(id);
        return new Result(true, StatusCode.SUCCESS, "Get one item success",detailItem);
    }

//    @GetMapping("/search")
//    private Result searchItemByBrand(@RequestParam("brands") List<String> brands){
//        List<ItemDTO> listItem = itemService.searchByBrand(brands);
//        return new Result(true, StatusCode.SUCCESS, "Search by brand success",listItem);
//    }

    @GetMapping("/search")
    private Result searchItemByBrandAndType(@RequestParam("typeId") Long typeId ,
                                            @RequestParam(value = "brands", required = false) List<String> brands){
        List<ItemDTO> listItem = itemService.searchByTypeAndBrand(typeId, brands);
        return new Result(true, StatusCode.SUCCESS, "Search by type and brand success",listItem);
    }
    @GetMapping("/search/{typeId}")
    private Result searchItemByType(@PathVariable("typeId") Long typeId){
        List<ItemDTO> listItem = itemService.searchByTypeId(typeId);
        return new Result(true, StatusCode.SUCCESS, "Search by type success",listItem);
    }


    /** Post method **/
    @PostMapping("/item")
    private Result addNewItem(@RequestBody NewItemDTO newItemDTO){
        ItemDTO savedItem = itemService.addNewItem(newItemDTO);
        return new Result(true, StatusCode.SUCCESS, "Add items success",savedItem);
    }



}
