package com.jibbyjabber.model.dto.message;

import java.util.List;

public class ChatMessageListDto {
    List<ChatMessageDto> chatLts;

    public ChatMessageListDto(List<ChatMessageDto> chatLts) {
        this.chatLts = chatLts;
    }

    public ChatMessageListDto() {
    }

    public List<ChatMessageDto> getChatLts() {
        return chatLts;
    }

    public void setChatLts(List<ChatMessageDto> chatLts) {
        this.chatLts = chatLts;
    }
}
