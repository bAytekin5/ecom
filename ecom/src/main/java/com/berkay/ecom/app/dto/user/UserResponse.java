package com.berkay.ecom.app.dto.user;

import com.berkay.ecom.app.dto.address.AddressDTO;
import com.berkay.ecom.app.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDTO address;
}
