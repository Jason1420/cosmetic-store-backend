package com.store.cosmetic.services;

import com.store.cosmetic.converter.UserConverter;
import com.store.cosmetic.dto.RegisterFormDTO;
import com.store.cosmetic.dto.UserDTO;
import com.store.cosmetic.entity.Customer;
import com.store.cosmetic.entity.Role;
import com.store.cosmetic.entity.UserEntity;
import com.store.cosmetic.repository.CustomerRepository;
import com.store.cosmetic.repository.RoleRepository;
import com.store.cosmetic.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO registerNewUser(RegisterFormDTO registerFormDTO) {
        if (userRepository.findOneByUsername(registerFormDTO.getUsername()) != null) {
            throw new EntityExistsException("This username have existed");
        }
        Set<Role> customerRole = roleRepository.findByName("CUSTOMER");
        Customer newCustomer = new Customer(registerFormDTO.getFullName(), registerFormDTO.getEmail(),
                registerFormDTO.getPhoneNumber(), registerFormDTO.getAddress());
        UserEntity newUser = new UserEntity(registerFormDTO.getUsername(),
                passwordEncoder.encode(registerFormDTO.getPassword()), customerRole, newCustomer);
        newCustomer.setUser(newUser);
        customerRepository.save(newCustomer);
        UserEntity savedUser = userRepository.save(newUser);
        UserDTO returnDTO = userConverter.toDto(savedUser);
        return returnDTO;
    }
}
