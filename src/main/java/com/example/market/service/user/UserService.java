package com.example.market.service.user;

import com.example.market.model.auth.Role;
import com.example.market.model.auth.User;
import com.example.market.model.auth.UserPrinciple;
import com.example.market.model.query.IUserChat;
import com.example.market.repository.IUserRepository;
import com.example.market.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.market.model.auth.RoleName.ROLE_USER;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        if (user.getRoles() == null) {
            Role role = roleService.findByName(ROLE_USER.toString());
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Iterable<IUserChat> getAllUserHasRoleUser() {
        List<User> users = (List<User>) userRepository.getAllUserHasRoleUser();
        List<IUserChat> userChats = new ArrayList<>();
        for (User user : users) {
            userChats.add(this.getUserChatInfo(user.getId()));
        }
        return userChats;
    }

    @Override
    public IUserChat getUserChatInfo(Long id) {
        return userRepository.getUserChatInfo(id);
    }
}
