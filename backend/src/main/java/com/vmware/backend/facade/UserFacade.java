package com.vmware.backend.facade;

import com.vmware.backend.model.User;
import com.vmware.backend.validator.UserAlreadyExistException;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;

import  java.util.List;

public interface UserFacade {
    User createUser(User user) throws UserAlreadyExistException;
    User getUser(String userId);
    List<User> getUsers();
    User getUserByUsername(String username);
    User checkCredentials(String username, String password) throws Exception;
}