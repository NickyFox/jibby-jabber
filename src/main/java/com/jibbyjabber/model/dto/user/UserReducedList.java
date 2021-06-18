package com.jibbyjabber.model.dto.user;

import java.util.List;

public class UserReducedList {
    List<UserReduced> users;

    public UserReducedList() {
    }

    public UserReducedList(List<UserReduced> users) {
        this.users = users;
    }

    public List<UserReduced> getUsers() {
        return users;
    }

    public void setUsers(List<UserReduced> users) {
        this.users = users;
    }
}
