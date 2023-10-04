package com.store.cosmetic.repository;

import com.store.cosmetic.dto.BrandDTO;
import com.store.cosmetic.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    ItemEntity findOneById(Long id);
    @Query( "select i from ItemEntity i where i.brand.name in :brands" )
    @Transactional
    List<ItemEntity> findByBrandContainingIgnoreCase(@Param("brands") List<String> brands);

    @Query( "select i from ItemEntity i where i.type.id = :typeId and i.brand.name in :brands" )
    List<ItemEntity> findByTypeAndBrandContainingIgnoreCase( @Param("typeId")Long typeId, @Param("brands") List<String> brands);

    List<ItemEntity> findAllByTypeId(Long typeId);
}
