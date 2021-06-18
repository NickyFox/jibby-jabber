package com.jibbyjabber.model.client;

import com.jibbyjabber.model.dto.post.PageablePostDto;
import com.jibbyjabber.model.dto.post.PostList;
import com.jibbyjabber.model.dto.post.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostClient {

    private final String USER_SERVICE_URL = "http://localhost:8083/post";
    @Autowired
    private RestTemplate restTemplate;

    public PostClient() {
    }


    public PostList getAllPosts(Long userId) {
        String url = USER_SERVICE_URL + "/getAll/" + userId;
        ResponseEntity<PostList> response = restTemplate.getForEntity(url,PostList.class);
        return response.getBody();
    }

    public PostList getHomePosts(PageablePostDto pageablePostDto) {
        String url = USER_SERVICE_URL + "/getHomePosts" ;
        ResponseEntity<PostList> response = restTemplate.postForEntity(url, pageablePostDto, PostList.class);
        return response.getBody();
    }

    public Posts createPost(Posts post) {
        String url = USER_SERVICE_URL + "/create" ;
        ResponseEntity<Posts> response = restTemplate.postForEntity(url, post, Posts.class);
        return response.getBody();
    }

    public long deletePost(long id) {
        String url = USER_SERVICE_URL + "/" + id ;
        restTemplate.delete(url);
        return id;
    }

    public Integer likePost(long postId, long userId) {
        String url = USER_SERVICE_URL + "/" + postId + "/like" ;
        return restTemplate.postForObject(url, userId, Integer.class);
    }
}
