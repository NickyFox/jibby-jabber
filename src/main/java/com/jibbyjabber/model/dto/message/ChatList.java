package com.jibbyjabber.model.dto.message;

import java.util.List;

public class ChatList {
    List<CompleteChat> chatDtoList;

    public ChatList(List<CompleteChat> chatDtoList) {
        this.chatDtoList = chatDtoList;
    }

    public ChatList() {
    }

    public List<CompleteChat> getChatDtoList() {
        return chatDtoList;
    }

    public void setChatDtoList(List<CompleteChat> chatDtoList) {
        this.chatDtoList = chatDtoList;
    }
}
