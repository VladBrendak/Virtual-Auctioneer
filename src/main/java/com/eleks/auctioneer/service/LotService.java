package com.eleks.auctioneer.service;

import com.eleks.auctioneer.DTO.LotDTO;
import com.eleks.auctioneer.entity.Lot;
import com.eleks.auctioneer.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class LotService {

@Autowired
    private LotRepository lotRepository;

    public ResponseEntity<String> uploadLot(LotDTO lotDTO, MultipartFile file, MultipartFile Image) {

        if (lotDTO.getName_of_lot() == null || file.isEmpty() || Image.isEmpty()) {
            return ResponseEntity.badRequest().body("Lot name, file, and preview image are mandatory.");
        }

        String assetFileName = saveFile(file, "${FILE_PATH}");
        String previewImageName = saveFile(Image, "${IMAGE_PATH}");

        Lot lot = lotDTO.mapToLot(lotDTO);
        lot.setAsset_file(assetFileName);
        lot.setImage(previewImageName);

        lotRepository.save(lot);

        return ResponseEntity.ok("Lot submitted successfully.");
    }
    private String saveFile(MultipartFile file, String storagePath) {

        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        return fileName;
    }

    public List<LotDTO> getAllActiveLots()
    {
        return lotRepository.findAllActiveLots().stream().map(Lot::mapToLotDTO).toList();
    }


}
