package com.vmware.backend.controller;

import com.vmware.backend.model.User;
import com.vmware.backend.service.impl.UserService;
import com.vmware.backend.validator.UserAlreadyExistException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("account")
public class AccountController {
    public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {

        System.out.println(">>>>>>" + newUser);
            return new ResponseEntity<User>(userService.createUser(newUser), HttpStatus.CREATED);

    }



    @GetMapping("/login")
    public ResponseEntity<User> user(@RequestParam String username, @RequestParam String password) {
        return new ResponseEntity<User>(userService.authentication(username, password), HttpStatus.OK);
    }

}
