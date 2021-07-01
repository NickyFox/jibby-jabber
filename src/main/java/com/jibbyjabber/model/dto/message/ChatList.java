package com.jibbyjabber.model.dto.message;

import java.util.List;

public class ChatList {
    List<ChatDto> chatDtoList;

    public ChatList(List<ChatDto> chatDtoList) {
        this.chatDtoList = chatDtoList;
    }

    public ChatList() {
    }

    public List<ChatDto> getChatDtoList() {
        return chatDtoList;
    }

    public void setChatDtoList(List<ChatDto> chatDtoList) {
        this.chatDtoList = chatDtoList;
    }
}
