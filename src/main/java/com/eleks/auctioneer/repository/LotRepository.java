package com.eleks.auctioneer.repository;

import com.eleks.auctioneer.entity.AppUser;
import com.eleks.auctioneer.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LotRepository extends JpaRepository<Lot, Long> {

    @Query("SELECT l FROM Lot l WHERE l.expiration > CURRENT_TIMESTAMP")
    List<Lot> findAllActiveLots();
    Optional<Lot> findById(long id_lot);
}
