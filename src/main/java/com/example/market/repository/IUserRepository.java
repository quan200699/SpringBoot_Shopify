package com.example.market.repository;

import com.example.market.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "select users.id, users.email, users.full_name, users.password " +
            "from market.users left join market.users_roles ur on users.id = ur.user_id " +
            "left join market.roles r on ur.role_id = r.id " +
            "where r.name = 'ROLE_USER'", nativeQuery = true)
    Iterable<User> getAllUserHasRoleUser();
}
