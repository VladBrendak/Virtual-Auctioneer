package com.eleks.auctioneer.service;

import com.eleks.auctioneer.DTO.BidDTO;
import com.eleks.auctioneer.entity.AppUser;
import com.eleks.auctioneer.entity.Bid;
import com.eleks.auctioneer.entity.Lot;
import com.eleks.auctioneer.repository.AuctionBidRepository;
import com.eleks.auctioneer.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class AuctionBidService {
    @Autowired
    private AuctionBidRepository auctionBidRepository;
    @Autowired
    private LotRepository lotRepository;

    public ResponseEntity<String> createBid(BidDTO bidDTO, AppUser appUser) {
        if (!isValidBid(bidDTO)) {
            return new ResponseEntity<String>("The betting time has expired!", HttpStatus.BAD_REQUEST);
        }

        Bid bid = BidDTO.mapToBid(bidDTO);
        bid.setUser(appUser);

        auctionBidRepository.save(bid);
        return new ResponseEntity<String>("The bet was placed successfully!", HttpStatus.OK);
    }

    private boolean isValidBid(BidDTO bidDTO) {
        Timestamp expiration = getLotExpiration(bidDTO.getLotId());

        if (expiration != null && expiration.after(new Timestamp(System.currentTimeMillis()))) {
            return true;
        }

        return false;
    }
    private Timestamp getLotExpiration(Long id_lot) {

        Optional<Lot> lotOptional = lotRepository.findById(id_lot);
        if (lotOptional.isPresent()) {
            Lot lot = lotOptional.get();
            return lot.getExpiration();
        }

        return null;
    }
}
