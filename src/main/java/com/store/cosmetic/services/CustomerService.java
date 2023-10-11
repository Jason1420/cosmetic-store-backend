package com.store.cosmetic.services;

import com.store.cosmetic.converter.CustomerConverter;
import com.store.cosmetic.dto.CustomerDTO;
import com.store.cosmetic.dto.NewCustomerInfoDTO;
import com.store.cosmetic.entity.Customer;
import com.store.cosmetic.entity.UserEntity;
import com.store.cosmetic.repository.CustomerRepository;
import com.store.cosmetic.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final UserRepository userRepository;

    public CustomerDTO changeInfo(NewCustomerInfoDTO newCustomerInfoDTO) {
        Customer oldCustomerInfo = customerRepository.findOneByUserId(newCustomerInfoDTO.getUserId());
        Customer newCustomerInfo = customerConverter.toNewInfo(newCustomerInfoDTO, oldCustomerInfo);
        Customer savedInfo = customerRepository.save(newCustomerInfo);
        UserEntity oldUser = userRepository.findOneById(newCustomerInfoDTO.getUserId());
        oldUser.setCustomer(savedInfo);
        userRepository.save(oldUser);
        CustomerDTO changedInfo = customerConverter.toDTO(savedInfo);
        return changedInfo;
    }
}
