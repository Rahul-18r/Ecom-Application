package com.app.ecom.controller;

import com.app.ecom.model.User;
import com.app.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("api/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("api/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.setUsers(user), HttpStatus.CREATED);
    }

    @GetMapping("api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        return userService.getUser(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("api/users/{id}")
    public  ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(id,user) ? "User Updated Successfully ":"User not Found....!",HttpStatus.CREATED);
    }
}
