package com.store.cosmetic.repository;

import com.store.cosmetic.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    BrandEntity findOneById(Long id);

    List<BrandEntity> findAll();

    BrandEntity findOneByName(String designationName);
}
