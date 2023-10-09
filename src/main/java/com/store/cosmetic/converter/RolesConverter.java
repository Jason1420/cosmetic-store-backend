package com.store.cosmetic.converter;

import com.store.cosmetic.dto.RoleDTO;
import com.store.cosmetic.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RolesConverter {

    public RoleDTO toDTO(Role entity) {
        return new RoleDTO(entity.getName());
    }
}
