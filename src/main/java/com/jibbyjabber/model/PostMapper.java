package com.jibbyjabber.model;

import com.jibbyjabber.model.dto.post.PostReduced;
import com.jibbyjabber.model.dto.post.Posts;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;

@Component
public class PostMapper {

    public PostMapper() {
    }

    public Posts postReducerToPost(PostReduced postReduced) {
        Posts post = new Posts();
        post.setContent(postReduced.getContent());
        post.setDate(new Timestamp(System.currentTimeMillis()));
        post.setUserId(postReduced.getUserId());
        post.setLikes(new ArrayList<>());
        post.setReposts(new ArrayList<>());
        post.setMedia(postReduced.getMedia());
        post.setThreads(new ArrayList<>());
        return post;
    }
}
