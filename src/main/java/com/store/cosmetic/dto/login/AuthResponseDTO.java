package com.store.cosmetic.dto.login;

import com.store.cosmetic.dto.UserDTO;
import lombok.Data;

@Data
public class AuthResponseDTO  {
    private final String accessToken;
    private final String tokenType = "Bearer ";
    private UserDTO userDTO;

    public AuthResponseDTO(String accessToken, UserDTO userDTO) {
        this.accessToken = accessToken;
        this.userDTO = userDTO;
    }



    @Override
    public String toString() {
        return "AccessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'';
    }
}
