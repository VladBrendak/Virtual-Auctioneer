package com.eleks.auctioneer.controller;

import com.eleks.auctioneer.DTO.BidDTO;
import com.eleks.auctioneer.entity.AppUser;
import com.eleks.auctioneer.service.AuctionBidService;
import com.eleks.auctioneer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/bids")
public class AuctionBidController {
    @Autowired
    private AuctionBidService auctionBidService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> makeBid(@Validated @RequestBody BidDTO bidDTO, Principal principal) {
        String currentUserEmail = principal.getName();
        AppUser appUser = userService.getUserByEmail(currentUserEmail);
        return auctionBidService.createBid(bidDTO, appUser);
    }
}
