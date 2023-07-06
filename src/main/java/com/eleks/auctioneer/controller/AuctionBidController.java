package com.eleks.auctioneer.controller;

import com.eleks.auctioneer.DTO.BidDTO;
import com.eleks.auctioneer.service.AuctionBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bids")
public class AuctionBidController {
    @Autowired
    private AuctionBidService auctionBidService;

    @PostMapping
    public ResponseEntity<String> makeBid(@Validated @RequestBody BidDTO bidDTO) {
        return auctionBidService.createBid(bidDTO);
    }
}
