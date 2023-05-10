package com.skylink.gympro.controller;

import com.skylink.gympro.model.User;
import com.skylink.gympro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.jetbrains.annotations.NotNull;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllRole(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @PostMapping("/create-users")
    public ResponseEntity<User>  createUser(@RequestBody User user){
        return ResponseEntity.ok(this.userService.createUser(user));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @PatchMapping("/update/users/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable long id , @RequestBody @NotNull User user){
        user.setUserId(id);
        return ResponseEntity.ok(this.userService.updateUser(user));
    }

    @DeleteMapping("/delete/users/{id}")
    public HttpStatus deleteUser(@PathVariable long id){
        this.userService.deleteUser(id);
        return HttpStatus.OK;
    }

}
