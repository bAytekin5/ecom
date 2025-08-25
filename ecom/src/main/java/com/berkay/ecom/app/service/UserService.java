package com.berkay.ecom.app.service;

import com.berkay.ecom.app.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<User>();
    private Long nextId = 1L;

    public List<User> fetchAllUsers() {
        return users;
    }

    public void addUser(User user) {
        user.setId(nextId++);
        users.add(user);
    }

    public User fetchUser(Long id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public boolean updateUser(Long id, User updatedUser) {

        return users.stream().filter(u -> u.getId() == id)
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    return true;
                }).orElse(false);
    }


}
