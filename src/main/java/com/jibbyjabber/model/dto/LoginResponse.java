package com.jibbyjabber.model.dto;

import com.jibbyjabber.model.dto.user.User;
import com.jibbyjabber.model.dto.user.UserReduced;

public class LoginResponse {
    String token;
    UserReduced user;

    public LoginResponse() {
    }

    public LoginResponse(String token, UserReduced user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserReduced getUser() {
        return user;
    }

    public void setUser(UserReduced user) {
        this.user = user;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
