package com.jibbyjabber.model.dto.user;

public class UserLogin {
    private final String email;
    private final String password;

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
