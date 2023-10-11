package com.store.cosmetic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCustomerInfoDTO {
    private Long userId;
    private String fullName;
    private String email;
    private String  address;
    private String  phoneNumber;
}
