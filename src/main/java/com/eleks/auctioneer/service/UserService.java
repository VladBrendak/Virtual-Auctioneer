package com.eleks.auctioneer.service;

import com.eleks.auctioneer.DTO.AppUserDTO;
import com.eleks.auctioneer.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(AppUserDTO userInfoDTO) {
        userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
        userInfoRepository.save(AppUserDTO.mapToUser(userInfoDTO));
        return "User added to system";
    }

}
