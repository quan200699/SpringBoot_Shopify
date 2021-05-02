package com.example.market.service.chat;

import com.example.market.model.Chat;
import com.example.market.service.IGeneralService;

public interface IChatService extends IGeneralService<Chat> {
    Iterable<Chat> getAllHistoryBetweenTwoUser(Long user1Id, Long user2Id, Integer size);
}
