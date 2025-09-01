package com.berkay.ecom.app.dto.user;

import com.berkay.ecom.app.dto.address.AddressDTO;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;
}
