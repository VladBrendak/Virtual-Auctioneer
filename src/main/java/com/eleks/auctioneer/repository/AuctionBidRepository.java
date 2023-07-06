package com.eleks.auctioneer.repository;

import com.eleks.auctioneer.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionBidRepository extends JpaRepository<Bid, Long> {
}
