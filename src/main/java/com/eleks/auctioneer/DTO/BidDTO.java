package com.eleks.auctioneer.DTO;

import com.eleks.auctioneer.entity.Bid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidDTO {
    private Long id_bid;
    private Long id_user;
    private Long id_lot;
    private Long bid;
    private Timestamp datetime;
    public static Bid mapToBid(BidDTO bidDTO){
        Bid bid = new Bid();
        bid.setId_bid(bidDTO.getId_bid());
        bid.setId_user(bidDTO.getId_user());
        bid.setId_lot(bidDTO.getId_lot());
        bid.setBid(bidDTO.getBid());
        bid.setDatetime(bidDTO.getDatetime());

        return bid;
    }

}
