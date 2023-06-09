package com.eleks.auctioneer.repository;

import com.eleks.auctioneer.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
