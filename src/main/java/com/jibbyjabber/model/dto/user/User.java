package com.jibbyjabber.model.dto.user;

public class User {
    private Long id;
    private String email;
    private String username;
    private String password;

    public User(Long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
