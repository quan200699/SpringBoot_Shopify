package com.example.market.service.user;

import com.example.market.model.User;
import com.example.market.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByEmail(String email);
}
