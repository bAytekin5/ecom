package com.berkay.ecom.app.service;

import com.berkay.ecom.app.dto.user.UserRequest;
import com.berkay.ecom.app.dto.user.UserResponse;
import com.berkay.ecom.app.dto.mapper.UserMapper;
import com.berkay.ecom.app.model.User;
import com.berkay.ecom.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> fetchAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public User addUser(UserRequest request) {
        User user = new User();
        userMapper.updateUserFromRequest(user, request);
        return userRepository.save(user);
    }


    public Optional<UserResponse> fetchUser(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapToUserResponse);
    }

    public User updateUser(Long id, UserRequest updatedUserRequest) {


        return userRepository.findById(id)
                .map(existingUser -> {
                    userMapper.updateUserFromRequest(existingUser, updatedUserRequest);
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }
}
