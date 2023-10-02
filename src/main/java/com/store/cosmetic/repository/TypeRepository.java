package com.store.cosmetic.repository;

import com.store.cosmetic.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<TypeEntity, Long> {
    TypeEntity findOneById(Long id);

    List<TypeEntity> findAll();

    TypeEntity findOneByName(String designationName);
}
