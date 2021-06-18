package com.jibbyjabber.model.dto.post;

import java.sql.Timestamp;
import java.util.List;

public class Posts {

    private Long id;
    private String content;
    private Timestamp date;
    private Long userId;
    private List<Long> likes;
    private List<Long>  reposts;
    private String media;
    private List<String> threads;

    public Posts() {

    }

    public Posts(Long id, String content, Timestamp date, Long userId, List<Long> likes, List<Long> reposts, String media, List<String> threads) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.userId = userId;
        this.likes = likes;
        this.reposts = reposts;
        this.media = media;
        this.threads = threads;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getDate() {
        return date;
    }

    public long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setLikes(List<Long> likes) {
        this.likes = likes;
    }

    public void setReposts(List<Long> reposts) {
        this.reposts = reposts;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public void setThreads(List<String> threads) {
        this.threads = threads;
    }

    public List<Long> getLikes() {
        return likes;
    }

    public List<Long> getReposts() {
        return reposts;
    }

    public String getMedia() {
        return media;
    }

    public List<String> getThreads() {
        return threads;
    }

}
