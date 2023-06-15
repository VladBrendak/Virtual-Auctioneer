package com.eleks.auctioneer.repository;

import com.eleks.auctioneer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNickname(String username);
}
