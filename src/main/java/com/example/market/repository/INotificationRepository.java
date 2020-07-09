package com.example.market.repository;

import com.example.market.model.Notification;
import com.example.market.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Long> {
    Iterable<Notification> findAllByStatusIsFalseAndUser(User user);

    @Query(value = "select * from market.notification where notification.user_id = ?1 " +
            "order by create_date desc ", nativeQuery = true)
    Iterable<Notification> findAllDateDesc(Long id);
}
