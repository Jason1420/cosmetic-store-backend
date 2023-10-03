package com.store.cosmetic.controller;

import com.store.cosmetic.dto.DefaultDataDTO;
import com.store.cosmetic.entity.BrandEntity;
import com.store.cosmetic.help.Result;
import com.store.cosmetic.help.StatusCode;
import com.store.cosmetic.services.BrandService;
import com.store.cosmetic.services.ItemService;
import com.store.cosmetic.services.TypeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DefaultDataAPI {

    private final ItemService itemService;
    private final BrandService brandService;

    @GetMapping("/getAllBrand")
    private Result getDefaultData(){
        List<BrandEntity> allBrand = brandService.getAllBrand();
        return new Result(true, StatusCode.SUCCESS, "Get all brands success",allBrand);
    }
}
