package com.eleks.auctioneer.repository;

import com.eleks.auctioneer.entity.Lot;
import org.hibernate.boot.model.internal.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotRepository extends JpaRepository<Lot, Long> {

    @Query("SELECT l FROM Lot l WHERE l.expiration > CURRENT_TIMESTAMP")
    List<Lot> findAllActiveLots();
}
