package com.store.cosmetic.dto.login;

import com.store.cosmetic.dto.CustomerDTO;
import com.store.cosmetic.dto.UserDTO;
import lombok.Data;

@Data
public class AuthResponseDTO  {
    private final String accessToken;
    private final String tokenType = "Bearer ";
    private UserDTO userDTO;
    private CustomerDTO customer;

    public AuthResponseDTO(String accessToken, UserDTO userDTO,CustomerDTO customer) {
        this.accessToken = accessToken;
        this.userDTO = userDTO;
        this.customer = customer;
    }



    @Override
    public String toString() {
        return "AccessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'';
    }
}
