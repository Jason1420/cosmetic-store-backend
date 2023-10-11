package com.store.cosmetic.repository;

import com.store.cosmetic.entity.Customer;
import com.store.cosmetic.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findOneByName(String username);


    Customer findOneByUserId(Long id);
}
