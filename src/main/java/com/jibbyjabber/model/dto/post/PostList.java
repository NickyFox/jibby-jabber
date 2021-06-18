package com.jibbyjabber.model.dto.post;

import java.util.List;

public class PostList {
    List<Posts> postsList;

    public PostList(List<Posts> postsList) {
        this.postsList = postsList;
    }

    public PostList() {
    }

    public List<Posts> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<Posts> postsList) {
        this.postsList = postsList;
    }
}
