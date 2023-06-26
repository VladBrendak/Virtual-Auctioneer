package com.eleks.auctioneer.repository;

import com.eleks.auctioneer.entity.Lot;
import org.hibernate.boot.model.internal.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepository extends JpaRepository<Lot, Long> {

}
