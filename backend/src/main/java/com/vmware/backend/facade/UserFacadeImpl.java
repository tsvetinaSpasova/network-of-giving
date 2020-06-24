package com.vmware.backend.facade;

import java.util.List;
import java.util.stream.Collectors;

import com.vmware.backend.model.User;
import com.vmware.backend.service.impl.UserService;
import com.vmware.backend.validator.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class UserFacadeImpl implements UserFacade {


    @Autowired
    private UserService userService;

    @Override
    public User createUser(final User user) throws UserAlreadyExistException {
        return userService.createUser(user);
    }

    @Override
    public User getUser(final String userId) {
        User userModel = userService.getUser(new Long(userId));
        return userModel;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUserByUsername(final String username) {
        User userModel = userService.getUserByUsername(username);
        return userModel;
    }

    @Override
    public User checkCredentials(String username, String password) throws Exception {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username was not found: " + username);
        }

        if (!user.getPassword().equals(password)) {
            throw new Exception();
        }

        return user;
    }
}