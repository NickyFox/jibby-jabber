package com.jibbyjabber.model.dto.post;

import java.sql.Timestamp;
import java.util.List;

public class PostWithUsername {

    private Long id;
    private String content;
    private Timestamp date;
    private Long userId;
    private List<Long> likes;
    private List<Long>  reposts;
    private String media;
    private List<String> threads;


}
