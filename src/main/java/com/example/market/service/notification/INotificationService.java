package com.example.market.service.notification;

import com.example.market.model.Notification;
import com.example.market.model.auth.User;
import com.example.market.service.IGeneralService;

public interface INotificationService extends IGeneralService<Notification> {
    Iterable<Notification> findAllByStatusIsFalseAndUser(User user);
}
