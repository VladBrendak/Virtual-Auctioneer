package com.eleks.auctioneer.service;

import com.eleks.auctioneer.DTO.AppUserDTO;
import com.eleks.auctioneer.entity.AppUser;
import com.eleks.auctioneer.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> userInfo = userInfoRepository.findByEmail(email);
        return userInfo.map(AppUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User email: \"" + email + "\" not found!"));
    }

    public ResponseEntity<String> addUser(AppUserDTO userInfoDTO) {
        if(userInfoRepository.findByEmail(userInfoDTO.getEmail()).isEmpty())
        {
            userInfoRepository.save(AppUserDTO.mapToUser(userInfoDTO, passwordEncoder));
            return new ResponseEntity<String>("User added to system", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Email already used!!!", HttpStatus.BAD_REQUEST);
    }

    public List<AppUserDTO> getAllUsers()
    {
        return userInfoRepository.findAll().stream().map(AppUser::mapToDto).toList();
    }

}