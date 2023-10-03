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
    private final ItemService itemService;
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

    @PostMapping("/item")
    private Result addNewItem(@RequestBody NewItemDTO newItemDTO){
        ItemDTO savedItem = itemService.addNewItem(newItemDTO);
        return new Result(true, StatusCode.SUCCESS, "Add items success",savedItem);
    }

}
