package com.store.cosmetic.dto;

import com.store.cosmetic.entity.BrandEntity;
import com.store.cosmetic.entity.StatusEntity;
import com.store.cosmetic.entity.TypeEntity;
import com.store.cosmetic.help.ItemBrand;
import com.store.cosmetic.help.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultDataDTO {
    private List<BrandEntity> brands;
    private List<TypeEntity> types;
    private List<StatusEntity> status;

}
