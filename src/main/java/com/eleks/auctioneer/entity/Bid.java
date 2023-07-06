package com.eleks.auctioneer.entity;

import com.eleks.auctioneer.DTO.BidDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Auction_bid")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_bid;
    private Long id_user;
    private Long id_lot;
    private Long bid;
    @CreationTimestamp
    private Timestamp datetime;

    public static BidDTO mapToBidDTO(Bid bid){
        BidDTO bidDTO = new BidDTO();
        bidDTO.setId_bid(bid.getId_bid());
        bidDTO.setId_user(bid.getId_user());
        bidDTO.setId_lot(bid.getId_lot());
        bidDTO.setBid(bid.getBid());
        bidDTO.setDatetime(bid.getDatetime());

        return bidDTO;
    }
}
