package com.eleks.auctioneer.controller;

import com.eleks.auctioneer.DTO.AppUserDTO;
import com.eleks.auctioneer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public String addNewUser(@Validated @RequestBody AppUserDTO userInfoDTO) {
        return userService.addUser(userInfoDTO);
    }

    @GetMapping("/getAll")
    public List<AppUserDTO> getAllUsers()
    {
        return userService.getAllUsers();
    }
}
