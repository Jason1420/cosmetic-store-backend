package com.store.cosmetic.controller;

import com.store.cosmetic.entity.BrandEntity;
import com.store.cosmetic.entity.TypeEntity;
import com.store.cosmetic.exception.helper.Result;
import com.store.cosmetic.exception.helper.StatusCode;
import com.store.cosmetic.services.BrandService;
import com.store.cosmetic.services.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DefaultDataAPI {

    private final BrandService brandService;
    private final TypeService typeService;
    private final RedisTemplate template;

    @GetMapping("/getAllBrand")
    private Result getDefaultBrandData() {
        boolean exists = template.hasKey("allBrands");
        if (exists) {
            List<BrandEntity> listItemRedis = (List<BrandEntity>) template.opsForValue().get("allBrands");
            return new Result(true, StatusCode.SUCCESS, "Get all brands success", listItemRedis);
        }
        List<BrandEntity> allBrand = brandService.getAllBrand();
        template.opsForValue().set("allBrands", allBrand, Duration.ofHours(24));
        return new Result(true, StatusCode.SUCCESS, "Get all brands success", allBrand);
    }

    @GetMapping("/getAllType")
    private Result getDefaultTypeData() {
        boolean exists = template.hasKey("allType");
        if (exists) {
            List<TypeEntity> listItemRedis = (List<TypeEntity>) template.opsForValue().get("allType");
            return new Result(true, StatusCode.SUCCESS, "Get all types success", listItemRedis);
        }
        List<TypeEntity> allType = typeService.getAllType();
        template.opsForValue().set("allType", allType, Duration.ofHours(24));
        return new Result(true, StatusCode.SUCCESS, "Get all types success", allType);
    }


}
