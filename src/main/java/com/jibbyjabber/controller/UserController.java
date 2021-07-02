package com.jibbyjabber.controller;

import com.jibbyjabber.model.client.UserClient;
import com.jibbyjabber.model.dto.user.*;
import com.jibbyjabber.security.JwtRequestFilter;
import com.jibbyjabber.security.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserClient userClient;
    private JwtRequestFilter jwtRequestFilter;
    private final JwtTokenUtil jwtTokenUtil;

    public UserController(UserClient userClient, JwtRequestFilter jwtRequestFilter, JwtTokenUtil jwtTokenUtil) {
        this.userClient = userClient;
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtTokenUtil = jwtTokenUtil;
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
        try {
            return new ResponseEntity<>(userClient.registerUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/password")
    public ResponseEntity<UserReduced> modifyPassword(@RequestBody String password) {
        try {
            String token = jwtRequestFilter.TOKEN;
            Long id = jwtTokenUtil.getUserIdFromToken(token);
            return new ResponseEntity(userClient.modifyPassword(password, id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UserReduced> updateUser(@RequestBody UsernameRequest username) {
        String token = jwtRequestFilter.TOKEN;
        Long id = jwtTokenUtil.getUserIdFromToken(token);
        return new ResponseEntity(userClient.modifyUser(username.getUsername(), id), HttpStatus.OK);

    }

    @PutMapping("/{id}/delete")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return new ResponseEntity(userClient.deleteUser(id), HttpStatus.OK);
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<List<UserReduced>> searchUsername(@PathVariable String username) {
        return new ResponseEntity<>(userClient.searchUsername(username).getUsers(), HttpStatus.OK);
    }

    @PostMapping("/follow/{userId}")
    public ResponseEntity<FollowerDto> followUser(@PathVariable Long userId) {
        String token = jwtRequestFilter.TOKEN;
        Long id = jwtTokenUtil.getUserIdFromToken(token);
        FollowerDto followerDto = new FollowerDto(userId, id);
        FollowerDto followerDtoResponse = userClient.followUser(followerDto);
        return new ResponseEntity(followerDtoResponse.getTo(), HttpStatus.OK);
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<UserReduced>> getFollowers(@PathVariable Long userId) {
        return new ResponseEntity<>(userClient.getFollowers(userId).getUsers(), HttpStatus.OK);
    }

    @GetMapping("/followings/{userId}")
    public ResponseEntity<List<UserReduced>> getFollowings(@PathVariable Long userId) {
        return new ResponseEntity<>(userClient.getFollowings(userId).getUsers(), HttpStatus.OK);
    }


}
