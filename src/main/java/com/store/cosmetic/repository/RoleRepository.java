package com.store.cosmetic.repository;

import com.store.cosmetic.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByName(String role);

    Role findOneByName(String student);

    Set<Role> findAllByName(String employee);
}
