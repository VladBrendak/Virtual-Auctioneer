package com.eleks.auctioneer.repository;

import com.eleks.auctioneer.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByNickname(String username);
    Optional<AppUser> findByEmail(String email);
    List<AppUser> findAll();
}
