package com.jibbyjabber.controller;

import com.jibbyjabber.model.client.MessageClient;
import com.jibbyjabber.model.client.UserClient;
import com.jibbyjabber.model.dto.message.*;
import com.jibbyjabber.model.dto.user.UserWithUsername;
import com.jibbyjabber.security.JwtRequestFilter;
import com.jibbyjabber.security.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/chat")
public class MessageController {

    private JwtRequestFilter jwtRequestFilter;
    private final JwtTokenUtil jwtTokenUtil;
    private final MessageClient messageClient;
    private final UserClient userClient;

    public MessageController(JwtRequestFilter jwtRequestFilter, JwtTokenUtil jwtTokenUtil, MessageClient messageClient, UserClient userClient) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.jwtTokenUtil = jwtTokenUtil;
        this.messageClient = messageClient;
        this.userClient = userClient;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatWithUsernames> getChat(@PathVariable long id){
        CompleteChat chat = messageClient.getChat(id);
        UserWithUsername userWithUsername1 = userClient.getUsername(chat.getUser1());
        UserWithUsername userWithUsername2 = userClient.getUsername(chat.getUser2());
        ChatWithUsernames chatWithUsernames = new ChatWithUsernames(userWithUsername1, userWithUsername2, chat.getMessages());
        return ResponseEntity.ok(chatWithUsernames);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChatWithUsernamesReduced>> getAllChats() {
        String token = jwtRequestFilter.TOKEN;
        Long id = jwtTokenUtil.getUserIdFromToken(token);
        ChatList chatMessageListDto = messageClient.getAllChats(id);
        List<ChatWithUsernamesReduced> chats = chatMessageListDto.getChatDtoList().stream().map(c -> {
            UserWithUsername user1 = userClient.getUsername(c.getUser1());
            UserWithUsername user2 = userClient.getUsername(c.getUser2());
            return new ChatWithUsernamesReduced(c.getId(), user1, user2, c.getMessages());
        }).collect(Collectors.toList());
        return ResponseEntity.ok(chats);
    }

}
