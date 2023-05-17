package com.skylink.gympro.controller;

import com.skylink.gympro.model.User;
import com.skylink.gympro.model.dto.UserDTO;
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
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @PostMapping("/create-users")
    public ResponseEntity<UserDTO>  createUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(this.userService.createUser(userDTO));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id){
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @PatchMapping("/update/users/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable long id , @RequestBody @NotNull UserDTO userDTO){
//        System.out.println(userDTO + "dfdfd" + id);
        userDTO.setUserId(id);
//        System.out.println(userDTO + " this is object1");
        return ResponseEntity.ok(this.userService.updateUser(userDTO));
    }

    @DeleteMapping("/delete/users/{id}")
    public HttpStatus deleteUser(@PathVariable long id){
        this.userService.deleteUser(id);
        return HttpStatus.OK;
    }

}
