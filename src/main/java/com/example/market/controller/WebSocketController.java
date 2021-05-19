package com.example.market.controller;

import com.example.market.model.Chat;
import com.example.market.model.Notification;
import com.example.market.model.Orders;
import com.example.market.service.chat.IChatService;
import com.example.market.service.notification.INotificationService;
import com.example.market.service.order.IOrdersService;
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

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private IOrdersService ordersService;

    @MessageMapping("/chats")
    @SendTo("/topic/chats")
    public Chat chatting(Chat chat) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        chat.setTime(date);
        chatService.save(chat);
        return chat;
    }

    @MessageMapping("/notifications")
    @SendTo("/topic/notifications")
    public Notification pushNotification(Notification notification){
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        notification.setCreateDate(date);
        notificationService.save(notification);
        return notification;
    }

    @MessageMapping("/orders")
    @SendTo("/topic/orders")
    public Orders addOrders(Orders orders){
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        orders.setCreateDate(date);
        return orders;
    }
}
