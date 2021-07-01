package com.jibbyjabber.model.dto.message;

public class ChatMessageDto {
    long sender;
    long receiver;
    String content;
    long date;

    public ChatMessageDto(long sender, long receiver, String content, long date) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = date;
    }

    public ChatMessageDto() {
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
