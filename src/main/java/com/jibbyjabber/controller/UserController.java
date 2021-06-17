package com.jibbyjabber.controller;

import com.jibbyjabber.model.client.UserClient;
import com.jibbyjabber.model.dto.User;
import com.jibbyjabber.model.dto.UserReduced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserClient userClient;

    @Autowired
    public UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserModel(@PathVariable Long id) {
        return new ResponseEntity("El usuario no esta registrado", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/available/{username}")
    public ResponseEntity<Boolean> isUsernameAvailable(@PathVariable String username) {
        return new ResponseEntity<>(!this.userClient.emailAvailable(username), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Long> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(userClient.registerUser(user), HttpStatus.OK);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<UserReduced> modifyUser(@RequestBody User user) {
            return new ResponseEntity(userClient.modifyPassword(user), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<UserReduced> updateUser(@RequestBody UserReduced user) {
        return new ResponseEntity(userClient.modifyUser(user), HttpStatus.OK);

    }

    @PutMapping("/{id}/delete")
    public ResponseEntity updateUser(@PathVariable Long id) {
        return new ResponseEntity(userClient.deleteUser(id), HttpStatus.OK);
    }


}
