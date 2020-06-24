package com.vmware.backend.service.impl;

import com.vmware.backend.facade.UserFacade;
import com.vmware.backend.model.AuthenticationToken;
import com.vmware.backend.model.User;
import com.vmware.backend.repository.UserRepository;
import com.vmware.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private TokenService tokenService;


    @Transactional
    @Override
    public User createUser(final User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User authentication(String username, String password) {
        User user = null;
        try {
            user = userFacade.checkCredentials(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AuthenticationToken authToken = tokenService.createAuthenticationToken(user);
        System.out.println(">>> ACCESS TOKEN " + authToken.getToken());
        user.setAccessToken(authToken.getToken());
        return user;
    }

    @Override
    public User getUser(final Long userId) {
        return (User) userRepository.findAllById(Collections.singleton(userId));
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        //Preconditions.checkNotNull(username);

        final User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username was not found: " + username);
        }

        return user;
    }

    @Override
    public User getUserByUsername(final String username) {
        return (User) loadUserByUsername(username);
    }

}