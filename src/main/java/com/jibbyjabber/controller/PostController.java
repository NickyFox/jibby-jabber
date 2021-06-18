package com.jibbyjabber.controller;

import com.jibbyjabber.model.PostMapper;
import com.jibbyjabber.model.client.PostClient;
import com.jibbyjabber.model.client.UserClient;
import com.jibbyjabber.model.dto.post.*;
import com.jibbyjabber.model.dto.user.UserReduced;
import com.jibbyjabber.model.dto.user.UserReducedList;
import com.jibbyjabber.security.JwtRequestFilter;
import com.jibbyjabber.security.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostClient postClient;
    private JwtRequestFilter jwtRequestFilter;
    private final JwtTokenUtil jwtTokenUtil;
    private final PostMapper postMapper;
    private final UserClient userClient;

    public PostController(PostClient postClient, JwtRequestFilter jwtRequestFilter, JwtTokenUtil jwtTokenUtil, PostMapper postMapper, UserClient userClient) {
        this.postClient = postClient;
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtTokenUtil = jwtTokenUtil;
        this.postMapper = postMapper;
        this.userClient = userClient;
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<PostList> getUserModel(@PathVariable Long userId) {
        return new ResponseEntity(postClient.getAllPosts(userId), HttpStatus.OK);
    }

    @GetMapping("/getHomePosts")
    public ResponseEntity<PostList> getHomePosts(){
        String token = jwtRequestFilter.TOKEN;
        Long id = jwtTokenUtil.getUserIdFromToken(token);
        UserReducedList userReducedList = userClient.getFollowings(id);
        List<Long> userIds = userReducedList.getUsers().stream().map(UserReduced::getId).collect(Collectors.toList());
        userIds.add(id);
        HomePosts homePosts = new HomePosts(userIds);
        PostList response = postClient.getHomePosts(homePosts);
        return new ResponseEntity(response.getPostsList(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Posts> createPost(@RequestBody PostReduced postReduced) {
        String token = jwtRequestFilter.TOKEN;
        Long id = jwtTokenUtil.getUserIdFromToken(token);
        postReduced.setUserId(id);
        Posts post = postMapper.postReducerToPost(postReduced);
        Posts postCreated = postClient.createPost(post);
        return new ResponseEntity<>(postCreated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Long> deletePost(@PathVariable long postId) {
        long postCreated = postClient.deletePost(postId);
        return new ResponseEntity<>(postCreated, HttpStatus.OK);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<Integer> likePost(@PathVariable long postId) {
        String token = jwtRequestFilter.TOKEN;
        Long id = jwtTokenUtil.getUserIdFromToken(token);
        return new ResponseEntity<>(postClient.likePost(postId, id), HttpStatus.OK);
    }

}
