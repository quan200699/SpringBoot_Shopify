package com.example.market.repository;

import com.example.market.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatRepository extends JpaRepository<Chat, Long> {
    @Query(value = "call market.getAllChatBetweenTwoUser(?1, ?2, ?3)", nativeQuery = true)
    Iterable<Chat> getAllHistoryBetweenTwoUser(Long user1Id, Long user2Id, Integer size);
}
