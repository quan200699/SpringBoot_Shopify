package com.example.market.service.chat;

import com.example.market.model.Chat;
import com.example.market.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatService implements IChatService {
    @Autowired
    private IChatRepository chatRepository;

    @Override
    public Iterable<Chat> findAll() {
        return chatRepository.findAll();
    }

    @Override
    public Optional<Chat> findById(Long id) {
        return chatRepository.findById(id);
    }

    @Override
    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public void remove(Long id) {
        chatRepository.deleteById(id);
    }

    @Override
    public Iterable<Chat> getAllHistoryBetweenTwoUser(Long user1Id, Long user2Id, Integer size) {
        return chatRepository.getAllHistoryBetweenTwoUser(user1Id, user2Id, size);
    }
}
