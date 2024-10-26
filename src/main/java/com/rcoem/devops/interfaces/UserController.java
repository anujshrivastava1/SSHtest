package com.rcoem.devops.interfaces;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rcoem.devops.User;
import com.rcoem.devops.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping()
    ResponseEntity<String> createUser(@RequestBody User user){
        return ResponseEntity.status(201).body(userService.createUser(user));
    }
    @GetMapping()
    List<User> createUser(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    User getUser(@PathVariable String id){
        return userService.getUser(id);
    }
    @PutMapping("/{id}")
    ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user) {
    User existingUser = userService.getUser(id);
    if (existingUser == null) {
        return ResponseEntity.status(404).body("User not found");
    }
    user.setUserId(id); 
    userService.updateUser(id, user);
    return ResponseEntity.status(200).body("User updated successfully");
}

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable String id) {
    if (userService.getUser(id) == null) {
        return ResponseEntity.status(404).body("User not found");
    }
    userService.deleteUser(id);
    return ResponseEntity.status(200).body("User deleted successfully");
}
}
