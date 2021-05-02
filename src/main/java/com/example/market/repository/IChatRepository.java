package com.example.market.repository;

import com.example.market.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatRepository extends JpaRepository<Chat, Long> {
    @Query(value = "select * " +
            "from market.chat " +
            "where (receiver_id = ?1 and sender_id = ?2) " +
            "or (receiver_id = ?2 and sender_id = ?1) " +
            "order by chat.time " +
            "limit ?3 " +
            "offset 0;", nativeQuery = true)
    Iterable<Chat> getAllHistoryBetweenTwoUser(Long user1Id, Long user2Id, Integer size);
}
