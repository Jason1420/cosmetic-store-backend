package com.store.cosmetic.controller;

import com.store.cosmetic.entity.BrandEntity;
import com.store.cosmetic.entity.TypeEntity;
import com.store.cosmetic.help.Result;
import com.store.cosmetic.help.StatusCode;
import com.store.cosmetic.services.BrandService;
import com.store.cosmetic.services.ItemService;
import com.store.cosmetic.services.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DefaultDataAPI {

    private final BrandService brandService;
    private final TypeService typeService;

    @GetMapping("/getAllBrand")
    private Result getDefaultBrandData(){
        List<BrandEntity> allBrand = brandService.getAllBrand();
        return new Result(true, StatusCode.SUCCESS, "Get all brands success",allBrand);
    }
    @GetMapping("/getAllType")
    private Result getDefaultTypeData(){
        List<TypeEntity> allType = typeService.getAllType();
        return new Result(true, StatusCode.SUCCESS, "Get all types success",allType);
    }
}
