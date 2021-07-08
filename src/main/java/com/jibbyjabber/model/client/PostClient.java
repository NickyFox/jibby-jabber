package com.jibbyjabber.model.client;

import com.jibbyjabber.model.dto.post.HomePosts;
import com.jibbyjabber.model.dto.post.PostList;
import com.jibbyjabber.model.dto.post.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostClient {

    private final String POST_SERVICE_URL = "http://jibby-jabber-posts:8083/post";
    @Autowired
    private RestTemplate restTemplate;

    public PostList getAllPosts(Long userId) {
        String url = POST_SERVICE_URL + "/getAll/" + userId;
        ResponseEntity<PostList> response = restTemplate.getForEntity(url,PostList.class);
        return response.getBody();
    }

    public PostList getHomePosts(HomePosts homePosts) {
        String url = POST_SERVICE_URL + "/getHomePosts" ;
        ResponseEntity<PostList> response = restTemplate.postForEntity(url, homePosts, PostList.class);
        return response.getBody();
    }

    public Posts createPost(Posts post) {
        String url = POST_SERVICE_URL + "/create" ;
        ResponseEntity<Posts> response = restTemplate.postForEntity(url, post, Posts.class);
        return response.getBody();
    }

    public long deletePost(long id) {
        String url = POST_SERVICE_URL + "/" + id ;
        restTemplate.delete(url);
        return id;
    }

    public Integer likePost(long postId, long userId) {
        String url = POST_SERVICE_URL + "/" + postId + "/like" ;
        return restTemplate.postForObject(url, userId, Integer.class);
    }
}
