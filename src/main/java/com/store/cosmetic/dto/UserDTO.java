package com.store.cosmetic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private List<String> roles;
    private CustomerDTO customer;

    public UserDTO( Long id,String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public UserDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
