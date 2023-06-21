package com.eleks.auctioneer.controller;

import com.eleks.auctioneer.DTO.AppUserDTO;
import com.eleks.auctioneer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public String addNewUser(@Validated @RequestBody AppUserDTO userInfoDTO) {
        return userService.addUser(userInfoDTO);
    }
}
