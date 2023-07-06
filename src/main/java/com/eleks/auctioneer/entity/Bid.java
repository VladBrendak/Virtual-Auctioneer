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
    @Column(name = "id_bid")
    private Long bidId;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private AppUser user;
    @Column(name = "id_lot")
    private Long lotId;
    private Long bid;
    @CreationTimestamp
    private Timestamp datetime;

    public static BidDTO mapToBidDTO(Bid bid){
        BidDTO bidDTO = new BidDTO();
        bidDTO.setBidId(bid.getBidId());
        bidDTO.setId_user(bid.getUser().getId());
        bidDTO.setLotId(bid.getLotId());
        bidDTO.setBid(bid.getBid());
        bidDTO.setDatetime(bid.getDatetime());

        return bidDTO;
    }
}
