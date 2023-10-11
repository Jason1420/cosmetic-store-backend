package com.store.cosmetic.converter;

import com.store.cosmetic.dto.CustomerDTO;
import com.store.cosmetic.dto.NewCustomerInfoDTO;
import com.store.cosmetic.entity.Customer;
import com.store.cosmetic.entity.UserEntity;
import com.store.cosmetic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerConverter {
    private final UserRepository userRepository;

    public CustomerDTO toDTO(Customer customerEntity) {
        if (customerEntity != null) {
            return new CustomerDTO(customerEntity.getName(),
                    customerEntity.getEmail(),
                    customerEntity.getAddress(),
                    customerEntity.getPhoneNumber());
        }
        return null;
    }

    public Customer toNewInfo(NewCustomerInfoDTO newCustomerInfoDTO, Customer oldCustomerInfo) {
        if (oldCustomerInfo != null) {
            oldCustomerInfo.setName(newCustomerInfoDTO.getFullName());
            oldCustomerInfo.setAddress(newCustomerInfoDTO.getAddress());
            oldCustomerInfo.setEmail(newCustomerInfoDTO.getEmail());
            oldCustomerInfo.setPhoneNumber(newCustomerInfoDTO.getPhoneNumber());
            return oldCustomerInfo;
        }
        UserEntity oldUser = userRepository.findOneById(newCustomerInfoDTO.getUserId());
        return new Customer(newCustomerInfoDTO.getFullName(),
                newCustomerInfoDTO.getEmail(),
                newCustomerInfoDTO.getPhoneNumber(),
                newCustomerInfoDTO.getAddress(),
                oldUser);
    }

}
