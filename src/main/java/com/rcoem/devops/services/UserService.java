package com.rcoem.devops.services;

import com.rcoem.devops.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    HashMap<String, User> users;

    @PostConstruct
    public void setup(){
        users=new HashMap<>();
    }
    // public String createUser(User user){
    //     user.setUserId(UUID.randomUUID().toString());
    //     users.put(user.getUserId(),user);
    //     return user.getUserId();
    // }

    public String createUser(User user) {
        if (users.values().stream()
                .anyMatch(existingUser -> existingUser.getName().equals(user.getName()) &&
                                          existingUser.getDepartment().equals(user.getDepartment()))) {
            throw new IllegalArgumentException("User with the same name and department already exists");
        }
        user.setUserId(UUID.randomUUID().toString());
        users.put(user.getUserId(), user);
        return user.getUserId();
    }

    public List<User> getAllUsers() {
    if (users.isEmpty()) {
        throw new NoSuchElementException("No users found");
    }
    return users.values().stream().collect(Collectors.toList());
    }


    public User getUser(String id) {
        User user = users.get(id);
        if (user == null) {
            throw new NoSuchElementException("User not found");
        }
        return user;
    }
    

    public void updateUser(String id, User user) {
        users.put(id, user); 
    }
    
    public void deleteUser(String id) {
        users.remove(id); 
    }
}
