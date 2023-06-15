package com.eleks.auctioneer.controller;

import com.eleks.auctioneer.entity.User;
import com.eleks.auctioneer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public String addNewUser(@RequestBody User userInfo) {
        return userService.addUser(userInfo);
    }
}
