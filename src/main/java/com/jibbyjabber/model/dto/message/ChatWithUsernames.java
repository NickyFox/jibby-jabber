package com.jibbyjabber.model.dto.message;

import com.jibbyjabber.model.dto.user.UserWithUsername;

import java.util.List;

public class ChatWithUsernames {
    UserWithUsername user1;
    UserWithUsername user2;
    List<ChatMessageDto> messages;

    public ChatWithUsernames(UserWithUsername user1, UserWithUsername user2, List<ChatMessageDto> messages) {
        this.user1 = user1;
        this.user2 = user2;
        this.messages = messages;
    }

    public ChatWithUsernames() {
    }

    public UserWithUsername getUser1() {
        return user1;
    }

    public void setUser1(UserWithUsername user1) {
        this.user1 = user1;
    }

    public UserWithUsername getUser2() {
        return user2;
    }

    public void setUser2(UserWithUsername user2) {
        this.user2 = user2;
    }

    public List<ChatMessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessageDto> messages) {
        this.messages = messages;
    }
}
