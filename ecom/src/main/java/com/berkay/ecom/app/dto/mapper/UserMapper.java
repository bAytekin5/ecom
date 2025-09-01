package com.berkay.ecom.app.dto.mapper;

import com.berkay.ecom.app.dto.address.AddressDTO;
import com.berkay.ecom.app.dto.user.UserRequest;
import com.berkay.ecom.app.dto.user.UserResponse;
import com.berkay.ecom.app.model.Address;
import com.berkay.ecom.app.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse mapToUserResponse(User user) {
        var id = user.getId();
        var addrr = user.getAddress();

        return UserResponse.builder()
                .id(id == null ? null : id.toString())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .address(addrr == null ? null :
                        AddressDTO.builder()
                                .street(addrr.getStreet())
                                .city(addrr.getCity())
                                .state(addrr.getState())
                                .country(addrr.getCountry())
                                .zipcode(addrr.getZipcode())
                                .build()
                )
                .build();
    }


    public void updateUserFromRequest(User user, UserRequest request) {
        var addrr = request.getAddress();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        if (request.getAddress() != null) {
            Address address = new Address();
            address.setStreet(addrr.getStreet());
            address.setState(addrr.getState());
            address.setZipcode(addrr.getZipcode());
            address.setCity(addrr.getCity());
            address.setCountry(addrr.getCountry());
            user.setAddress(address);
        }
    }
}
