package com.store.cosmetic.repository;

import com.store.cosmetic.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
    StatusEntity findOneById(Long id);

    List<StatusEntity> findAll();

    StatusEntity findOneByName(String designationName);
}
