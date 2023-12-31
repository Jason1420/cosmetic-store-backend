package com.store.cosmetic.controller;

import com.store.cosmetic.dto.DefaultDataDTO;
import com.store.cosmetic.dto.ItemDTO;
import com.store.cosmetic.dto.NewItemDTO;
import com.store.cosmetic.exception.helper.Result;
import com.store.cosmetic.exception.helper.StatusCode;
import com.store.cosmetic.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ItemAPI {

    /**
     * Declare Bean
     **/
    private final ItemService itemService;
    private final RedisTemplate template;

    /**
     * Get method
     **/
    @GetMapping("/getAll")
    public Result getAllItems() {
        boolean exists = template.hasKey("allItem");
        if (exists) {
            List<ItemDTO> listItemRedis = (List<ItemDTO>) template.opsForValue().get("allItem");
            return new Result(true, StatusCode.SUCCESS, "Get all items success", listItemRedis);
        }
        List<ItemDTO> listAllItem = itemService.getAllItems();
        template.opsForValue().set("allItem", listAllItem, Duration.ofDays(7));
        return new Result(true, StatusCode.SUCCESS, "Get all items success", listAllItem);
    }

    @GetMapping("/getDefaultData")
    private Result getDefaultData() {
        boolean exists = template.hasKey("defaultData");
        if (exists) {
            DefaultDataDTO defaultDataRedis = (DefaultDataDTO) template.opsForValue().get("defaultData");
            return new Result(true, StatusCode.SUCCESS, "Get default items success", defaultDataRedis);
        }
        DefaultDataDTO defaultData = itemService.getDefaultData();
        template.opsForValue().set("defaultData", defaultData, Duration.ofDays(7));
        return new Result(true, StatusCode.SUCCESS, "Get default data success", defaultData);
    }

    @GetMapping("/getOneItem/{id}")
    private Result getOneItem(@PathVariable("id") Long id) {
        boolean exists = template.hasKey("allItem");
        if (exists) {
            List<ItemDTO> listItemRedis = (List<ItemDTO>) template.opsForValue().get("allItem");
            ItemDTO itemRedis = listItemRedis.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
            return new Result(true, StatusCode.SUCCESS, "Get one items success", itemRedis);
        }
        List<ItemDTO> listAllItem = itemService.getAllItems();
        template.opsForValue().set("allItem", listAllItem, Duration.ofDays(7));
        ItemDTO itemRedis = listAllItem.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
        return new Result(true, StatusCode.SUCCESS, "Get one item success", itemRedis);
    }


    @GetMapping("/search")
    private Result searchItemByBrandAndTypePagination(@RequestParam("typeId") Long typeId,
                                                      @RequestParam(value = "brands", required = false) List<String> brands,
                                                      @RequestParam("page") int page) {
        String redisKey = "searchItemByBrandAndTypePagination" + typeId + brands.toString() + page;
        boolean exists = template.hasKey(redisKey);
        if (exists) {
            List<ItemDTO> listItemRedis = (List<ItemDTO>) template.opsForValue().get(redisKey);
            return new Result(true, StatusCode.SUCCESS, "Search by type and brand success", listItemRedis);
        }
        List<ItemDTO> listItem = itemService.searchByTypeAndBrand(typeId, brands, page);
        template.opsForValue().set(redisKey, listItem, Duration.ofDays(7));
        return new Result(true, StatusCode.SUCCESS, "Search by type and brand success", listItem);
    }

    @GetMapping("/count-item-by-typeid-brands")
    private Result countItemByBrandAndTypePagination(@RequestParam("typeId") Long typeId,
                                                     @RequestParam(value = "brands", required = false) List<String> brands) {
        String redisKey = "countItemByBrandAndTypePagination" + typeId + brands.toString();
        boolean exists = template.hasKey(redisKey);
        if (exists) {
            Long quantityRedis = Long.parseLong(String.valueOf(template.opsForValue().get(redisKey)));
            return new Result(true, StatusCode.SUCCESS, "Count success", quantityRedis);
        }
        Long quantity = itemService.countItemByBrandAndTypePagination(typeId, brands);
        template.opsForValue().set(redisKey, quantity, Duration.ofDays(7));
        return new Result(true, StatusCode.SUCCESS, "Count success", quantity);
    }

    @GetMapping("/count-item-by-typeid")
    private Result countItemByTypeName(@RequestParam("typeId") Long typeId) {

        String redisKey = "countItemByTypeName" + typeId;
        boolean exists = template.hasKey(redisKey);
        if (exists) {
            Long quantityRedis = Long.parseLong(String.valueOf(template.opsForValue().get(redisKey)));
            return new Result(true, StatusCode.SUCCESS, "Count success", quantityRedis);
        }
        Long quantity = itemService.countItemByTypeName(typeId);
        template.opsForValue().set(redisKey, quantity, Duration.ofDays(7));
        return new Result(true, StatusCode.SUCCESS, "Count success", quantity);
    }

    @GetMapping("/search/{typeId}")
    private Result searchItemByTypePagination(@PathVariable("typeId") Long typeId,
                                              @RequestParam("page") int page) {

        String redisKey = "searchItemByTypePagination" + typeId + page;
        boolean exists = template.hasKey(redisKey);
        if (exists) {
            List<ItemDTO> listItemRedis = (List<ItemDTO>) template.opsForValue().get(redisKey);
            return new Result(true, StatusCode.SUCCESS, "Search by type success", listItemRedis);
        }
        List<ItemDTO> listItem = itemService.searchByTypeId(typeId, page);
        template.opsForValue().set(redisKey, listItem, Duration.ofDays(7));
        return new Result(true, StatusCode.SUCCESS, "Search by type success", listItem);
    }


    @GetMapping("/hot-deal")
    private Result searchHotDealItem(@RequestParam("page") int page) {
        boolean exists = template.hasKey("hotdeal" + page);
        if (exists) {
            List<ItemDTO> listItemRedis = (List<ItemDTO>) template.opsForValue().get("hotdeal" + page);
            return new Result(true, StatusCode.SUCCESS, "Search hot-deal items success", listItemRedis);
        }
        List<ItemDTO> listItem = itemService.searchHotDealItem(page);
        template.opsForValue().set("hotdeal" + page, listItem, Duration.ofDays(7));
        return new Result(true, StatusCode.SUCCESS, "Search hot-deal items success", listItem);
    }

    @GetMapping("/all-hot-item")
    private Result findAllHotDealItem() {
        boolean exists = template.hasKey("allItem");
        if (exists) {
            List<ItemDTO> listItemRedis = (List<ItemDTO>) template.opsForValue().get("allItem");
            List<ItemDTO> listItem = listItemRedis.stream().
                    filter(item -> item.getStatus().equals("Hot")).collect(Collectors.toList());
            return new Result(true, StatusCode.SUCCESS, "Find all hot-deal items success", listItem);
        }
        List<ItemDTO> listAllItem = itemService.getAllItems();
        template.opsForValue().set("allItem", listAllItem, Duration.ofDays(7));
        List<ItemDTO> listItem = listAllItem.stream().
                filter(item -> item.getStatus().equals("Hot")).collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find all hot-deal items success", listItem);
    }

    @GetMapping("/new-item")
    private Result searchNewItem(@RequestParam("page") int page) {
        boolean exists = template.hasKey("newItem" + page);
        if (exists) {
            List<ItemDTO> listItemRedis = (List<ItemDTO>) template.opsForValue().get("newItem" + page);
            return new Result(true, StatusCode.SUCCESS, "Search new items success", listItemRedis);
        }
        List<ItemDTO> listItem = itemService.searchNewItem(page);
        template.opsForValue().set("newItem" + page, listItem, Duration.ofDays(7));
        return new Result(true, StatusCode.SUCCESS, "Search new items success", listItem);
    }

    @GetMapping("/all-new-item")
    private Result findAllNewItem() {
        boolean exists = template.hasKey("allItem");
        if (exists) {
            List<ItemDTO> listItemRedis = (List<ItemDTO>) template.opsForValue().get("allItem");
            List<ItemDTO> listItem = listItemRedis.stream().
                    filter(item -> item.getStatus().equals("New")).collect(Collectors.toList());
            return new Result(true, StatusCode.SUCCESS, "Find all new items success", listItem);
        }
        List<ItemDTO> listAllItem = itemService.getAllItems();
        template.opsForValue().set("allItem", listAllItem, Duration.ofDays(7));
        List<ItemDTO> listItem = listAllItem.stream().
                filter(item -> item.getStatus().equals("New")).collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find all new items success", listItem);

    }

    @GetMapping("/best-sellers")
    private Result searchBestSellers(@RequestParam("page") int page) {
        boolean exists = template.hasKey("bestSellers" + page);
        if (exists) {
            List<ItemDTO> listItemRedis = (List<ItemDTO>) template.opsForValue().get("bestSellers" + page);
            return new Result(true, StatusCode.SUCCESS, "Search best sellers success", listItemRedis);
        }
        List<ItemDTO> listItem = itemService.searchBestSellers(page);
        template.opsForValue().set("bestSellers" + page, listItem, Duration.ofDays(7));
        return new Result(true, StatusCode.SUCCESS, "Search best sellers success", listItem);
    }

    @GetMapping("/all-best-sellers")
    private Result findAllBestSellers() {
        boolean exists = template.hasKey("allItem");
        if (exists) {
            List<ItemDTO> listItemRedis = (List<ItemDTO>) template.opsForValue().get("allItem");
            List<ItemDTO> listItem = listItemRedis.stream().
                    filter(item -> item.getStatus().equals("Best")).collect(Collectors.toList());
            return new Result(true, StatusCode.SUCCESS, "Find all best sellers success", listItem);
        }
        List<ItemDTO> listAllItem = itemService.getAllItems();
        template.opsForValue().set("allItem", listAllItem, Duration.ofDays(7));
        List<ItemDTO> listItem = listAllItem.stream().
                filter(item -> item.getStatus().equals("Best")).collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find all best sellers success", listItem);

    }

    @GetMapping("/gift")
    private Result searchGift(@RequestParam("page") int page) {
        boolean exists = template.hasKey("gift" + page);
        if (exists) {
            List<ItemDTO> listItemRedis = (List<ItemDTO>) template.opsForValue().get("gift" + page);
            return new Result(true, StatusCode.SUCCESS, "Search best sellers success", listItemRedis);
        }
        List<ItemDTO> listItem = itemService.searchGift(page);
        template.opsForValue().set("gift" + page, listItem, Duration.ofDays(7));
        return new Result(true, StatusCode.SUCCESS, "Search best sellers success", listItem);

    }

    @GetMapping("/all-gift")
    private Result findAllGift() {
        boolean exists = template.hasKey("allItem");
        if (exists) {
            List<ItemDTO> listItemRedis = (List<ItemDTO>) template.opsForValue().get("allItem");
            List<ItemDTO> listItem = listItemRedis.stream().
                    filter(item -> item.getStatus().equals("Gift")).collect(Collectors.toList());
            return new Result(true, StatusCode.SUCCESS, "Find all gift success", listItem);
        }
        List<ItemDTO> listAllItem = itemService.getAllItems();
        template.opsForValue().set("allItem", listAllItem, Duration.ofDays(7));
        List<ItemDTO> listItem = listAllItem.stream().
                filter(item -> item.getStatus().equals("Gift")).collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Find all gift success", listItem);
    }

    /**
     * Post method
     **/
    @PostMapping("/item")
    private Result addNewItem(@RequestBody NewItemDTO newItemDTO) {
        ItemDTO savedItem = itemService.addNewItem(newItemDTO);
        template.getConnectionFactory().getConnection().flushAll();
        return new Result(true, StatusCode.SUCCESS, "Add items success", savedItem);
    }


}
