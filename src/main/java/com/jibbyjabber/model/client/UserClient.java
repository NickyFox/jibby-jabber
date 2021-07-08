package com.jibbyjabber.model.client;

import com.jibbyjabber.model.dto.user.*;
import com.jibbyjabber.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {
    private final String USER_SERVICE_URL = "http://jibby-jabber-users:8082/users";
    private final PasswordEncoder passwordEncoderConfig;

    @Autowired
    private RestTemplate restTemplate;

    public UserClient(PasswordEncoder passwordEncoderConfig) {
        this.passwordEncoderConfig = passwordEncoderConfig;
    }

    public boolean emailAvailable(String email) {
        String url = USER_SERVICE_URL + "/available/" + email;
        ResponseEntity<Boolean> response = restTemplate.getForEntity(url,Boolean.class);
        return response.getBody();
    }

    public User getUser(long userId) {
        String url = USER_SERVICE_URL + "/" + userId;
        ResponseEntity<User> response = restTemplate.getForEntity(url,User.class);
        return response.getBody();
    }

    public User getUserByEmail(String email) {
        String url = USER_SERVICE_URL + "/email/" + email;
        ResponseEntity<User> response = restTemplate.getForEntity(url,User.class);
        return response.getBody();
    }

    public UserReduced modifyUser(String username, long userId) {
        String url = USER_SERVICE_URL + "/" + userId + "/update";
        ResponseEntity<UserReduced> response = restTemplate.postForEntity(url, username, UserReduced.class);
        return response.getBody();
    }

    public UserReduced modifyPassword(String password, long id) {
            String encodedPassword = passwordEncoderConfig.encoder().encode(password);
            String url = USER_SERVICE_URL + "/" + id + "/password";
            ResponseEntity<UserReduced> response = restTemplate.postForEntity(url, encodedPassword, UserReduced.class);
            return response.getBody();
    }

    public Long registerUser(User user) {
        user.setPassword(passwordEncoderConfig.encoder().encode(user.getPassword()));
        String url = USER_SERVICE_URL + "/register";
        return restTemplate.postForObject(url, user, Long.class);
    }

    public Long deleteUser(long userId) {
        String url = USER_SERVICE_URL + "/";
        restTemplate.delete(url, userId);
        return userId;
    }

    public UserReducedList searchUsername(String username) {
        String url = USER_SERVICE_URL + "/search/" + username;
        ResponseEntity<UserReducedList> response = restTemplate.getForEntity(url,UserReducedList.class);
        return response.getBody();
    }

    public FollowerDto followUser(FollowerDto followerDto) {
        String url = USER_SERVICE_URL + "/follow";
        return restTemplate.postForObject(url, followerDto, FollowerDto.class);
    }

    public UserReducedList getFollowers(Long id) {
        String url = USER_SERVICE_URL + "/followers/" + id;
        ResponseEntity<UserReducedList> response = restTemplate.getForEntity(url,UserReducedList.class);
        return response.getBody();
    }

    public UserReducedList getFollowings(Long id) {
        String url = USER_SERVICE_URL + "/followings/" + id;
        ResponseEntity<UserReducedList> response = restTemplate.getForEntity(url,UserReducedList.class);
        return response.getBody();
    }

    public UserWithUsername getUsername(Long id) {
        String url = USER_SERVICE_URL + "/getUsername/" + id;
        ResponseEntity<UserWithUsername> response = restTemplate.getForEntity(url,UserWithUsername.class);
        return response.getBody();
    }
}
