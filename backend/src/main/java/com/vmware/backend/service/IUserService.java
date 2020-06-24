package com.vmware.backend.service;

import com.vmware.backend.model.User;
import com.vmware.backend.validator.UserAlreadyExistException;
import com.vmware.backend.validator.UserDoesnExistException;
import com.vmware.backend.validator.WrongPasswordException;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

public interface IUserService {
//    User save(User user);
//    User findByUsername(String username);
//    User registration(User newUser) throws UserAlreadyExistException;
//    UserDetails loadUserByUsername(String username);
//    User login(String username, String password) throws WrongPasswordException, UserDoesnExistException;
User createUser(User user);
    User getUser(Long userId);
    List<User> getUsers();
    User getUserByUsername(String username);
    User authentication(String username, String password);
}
