package com.eleks.auctioneer.service;

import com.eleks.auctioneer.DTO.BidDTO;
import com.eleks.auctioneer.entity.Bid;
import com.eleks.auctioneer.repository.AuctionBidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuctionBidService {
    @Autowired
    private AuctionBidRepository auctionBidRepository;

    public ResponseEntity<String> createBid(BidDTO bidDTO) {
        // Перевірка дійсності ставки, наприклад, чи ставка більше попередньої
        if (!isValidBid(bidDTO)) {
            throw new IllegalArgumentException("Invalid bid amount");
        }

        // Мапування з DTO до сутності Bid
        Bid bid = BidDTO.mapToBid(bidDTO);

        // Збереження ставки
        auctionBidRepository.save(bid);
        return null;
    }

    private boolean isValidBid(BidDTO bidDTO) {
        // Перевірка дійсності ставки
        // Наприклад, порівняння з попередньою ставкою або перевірка максимального значення

        return true; // Повертаємо true, якщо ставка є дійсною
    }
}
