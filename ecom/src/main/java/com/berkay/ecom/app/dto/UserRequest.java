package com.berkay.ecom.app.dto;

import com.berkay.ecom.app.model.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;
}
