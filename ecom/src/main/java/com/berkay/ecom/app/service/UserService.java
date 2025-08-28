package com.berkay.ecom.app.service;

import com.berkay.ecom.app.model.User;
import com.berkay.ecom.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private List<User> users = new ArrayList<User>();
//    private Long nextId = 1L;

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
//        return users;
    }

    public User addUser(User user) {
        userRepository.save(user);
//        user.setId(nextId++);
//        users.add(user);
        return user;
    }

    public Optional<User> fetchUser(Long id) {
        return userRepository.findById(id);
//        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public boolean updateUser(Long id, User updatedUser) {

        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    userRepository.save(existingUser);
                    return true;
                })
                .orElse(false);

//        return users.stream().filter(u -> u.getId() == id)
//                .findFirst()
//                .map(existingUser -> {
//                    existingUser.setFirstName(updatedUser.getFirstName());
//                    existingUser.setLastName(updatedUser.getLastName());
//                    return true;
//                }).orElse(false);
    }


}
