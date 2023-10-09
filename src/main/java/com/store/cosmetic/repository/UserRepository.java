package com.store.cosmetic.repository;

import com.store.cosmetic.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUsername(String username);

    UserEntity findOneById(Long id);

    UserEntity findOneByEmployeeId(Long id);
}
