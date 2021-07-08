package com.jibbyjabber.model.client;

import com.jibbyjabber.model.dto.message.ChatList;
import com.jibbyjabber.model.dto.message.CompleteChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MessageClient {
    private final String MESSAGE_SERVICE_URL = "http://jibby-jabber-messages-dev:8084/";

    @Autowired
    private RestTemplate restTemplate;

    public ChatList getAllChats(Long userId) {
        String url = MESSAGE_SERVICE_URL + "chats/" + userId;
        ResponseEntity<ChatList> response = restTemplate.getForEntity(url, ChatList.class);
        return response.getBody();
    }

    public CompleteChat getChat(Long chatId) {
        String url = MESSAGE_SERVICE_URL + "chat/messages/" + chatId;
        ResponseEntity<CompleteChat> response = restTemplate.getForEntity(url, CompleteChat.class);
        return response.getBody();
    }
}
