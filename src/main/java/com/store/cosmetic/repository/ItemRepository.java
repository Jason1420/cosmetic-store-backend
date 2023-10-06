package com.store.cosmetic.repository;

import com.store.cosmetic.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    ItemEntity findOneById(Long id);

    @Query("select i from ItemEntity i where i.type.id = :typeId and i.brand.name in :brands")
    List<ItemEntity> findByTypeAndBrandContainingIgnoreCase(@Param("typeId") Long typeId, @Param("brands") List<String> brands,PageRequest of);

    List<ItemEntity> findAllByTypeId(Long typeId,PageRequest of);

    Long countAllByType_Id(Long typeId);


    @Query("select i from ItemEntity i where i.status.name = 'Hot' ")
    Page<ItemEntity> findByStatusHot(PageRequest of);

    @Query("select i from ItemEntity i where i.status.name = 'Hot' ")
    List<ItemEntity> findAllHotDealItem();

    @Query("select i from ItemEntity i where i.status.name = 'New' ")
    Page<ItemEntity> findByStatusNew(PageRequest of);

    @Query("select i from ItemEntity i where i.status.name = 'New' ")
    List<ItemEntity> findAllNewItem();

    @Query("select i from ItemEntity i where i.status.name = 'Best' ")
    Page<ItemEntity> searchBestSellers(PageRequest of);

    @Query("select i from ItemEntity i where i.status.name = 'Best' ")
    List<ItemEntity> findAllBestSellers();
    @Query("select i from ItemEntity i where i.status.name = 'Gift' ")
    Page<ItemEntity> searchGift(PageRequest of);

    @Query("select i from ItemEntity i where i.status.name = 'Gift' ")
    List<ItemEntity> findAllGift();

    @Query("select count(i) from ItemEntity i where i.type.id = :typeId and i.brand.name in :brands")
    Long countItemByBrandAndTypePagination(Long typeId, List<String> brands);
}
