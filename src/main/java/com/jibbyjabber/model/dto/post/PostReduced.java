package com.jibbyjabber.model.dto.post;

public class PostReduced {
    private String content;
    private Long userId;
    private String media;

    public PostReduced(String content, Long userId, String media) {
        this.content = content;
        this.userId = userId;
        this.media = media;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }

    public String getMedia() {
        return media;
    }
}
