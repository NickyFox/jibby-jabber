package com.jibbyjabber.model.dto.message;

import java.sql.Timestamp;

public class ChatMessageDto {
    long sender;
    long receiver;
    String content;
    Timestamp date;

    public ChatMessageDto(long sender, long receiver, String content, Timestamp date) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = date;
    }

    public ChatMessageDto() {
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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
