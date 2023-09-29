package com.store.cosmetic.controller;

import com.store.cosmetic.dto.ItemDTO;
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

    @PostMapping("/item")
    private Result addNewItem(@RequestBody ItemDTO itemDTO){
        ItemDTO savedItem = itemService.addNewItem(itemDTO);
        return new Result(true, StatusCode.SUCCESS, "Add items success");
    }

}
