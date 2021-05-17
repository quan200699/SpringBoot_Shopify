package com.example.market.controller;

import com.example.market.model.Chat;
import com.example.market.service.chat.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin("*")
@RestController
public class WebSocketController {
    @Autowired
    private IChatService chatService;

    @MessageMapping("/chats")
    @SendTo("/topic/chats")
    public Chat chatting(Chat chat) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        chat.setTime(date);
        chatService.save(chat);
        return chat;
    }
}
