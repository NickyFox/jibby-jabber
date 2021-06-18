package com.jibbyjabber.model.dto.user;

import java.util.ArrayList;
import java.util.List;

public class FollowerList {
    private List<FollowerDto> followerDtoList;

    public FollowerList(List<FollowerDto> followerDtoList) {
        this.followerDtoList = followerDtoList;
    }

    public FollowerList() {
    }

    public List<FollowerDto> getFollowerDtoList() {
        return followerDtoList;
    }

    public void setFollowerDtoList(List<FollowerDto> followerDtoList) {
        this.followerDtoList = followerDtoList;
    }
}
