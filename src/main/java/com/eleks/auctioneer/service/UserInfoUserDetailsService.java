package com.eleks.auctioneer.service;

import com.eleks.auctioneer.entity.AppUser;
import com.eleks.auctioneer.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> userInfo = userInfoRepository.findByEmail(email);
        return userInfo.map(AppUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User email: \"" + email + "\" not found!"));
    }
}
