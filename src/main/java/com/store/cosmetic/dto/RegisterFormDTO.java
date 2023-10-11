package com.store.cosmetic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterFormDTO {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;
}
