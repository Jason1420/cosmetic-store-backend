package com.store.cosmetic.converter;

import com.store.cosmetic.dto.UserDTO;
import com.store.cosmetic.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserConverter {
    public UserEntity toEntity(UserDTO dto) {
        return new UserEntity(dto.getUsername(),
                dto.getPassword());
    }

    public UserDTO toDto(UserEntity entity) {
        return new UserDTO(entity.getId(),
                entity.getUsername(),
                entity.getPassword());
    }

    public UserDTO toDtoAfterLogin(UserEntity userEntity) {
        return new UserDTO(userEntity.getUsername(),
                userEntity.getRoles().stream().map(role -> {
                    return role.getName();
                }).collect(Collectors.toList())
        );
    }
}
