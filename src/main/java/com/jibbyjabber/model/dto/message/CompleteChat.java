package com.jibbyjabber.model.dto.message;

import java.util.List;

public class CompleteChat {
    long id;
    long user1;
    long user2;
    List<ChatMessageDto> messages;

    public CompleteChat(long id, long user1, long user2, List<ChatMessageDto> messages) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.messages = messages;
    }

    public CompleteChat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser1() {
        return user1;
    }

    public void setUser1(long user1) {
        this.user1 = user1;
    }

    public long getUser2() {
        return user2;
    }

    public void setUser2(long user2) {
        this.user2 = user2;
    }

    public List<ChatMessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessageDto> messages) {
        this.messages = messages;
    }
}
