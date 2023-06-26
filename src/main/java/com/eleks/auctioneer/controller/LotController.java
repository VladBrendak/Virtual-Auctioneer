package com.eleks.auctioneer.controller;


import com.eleks.auctioneer.DTO.LotDTO;
import com.eleks.auctioneer.entity.Lot;
import com.eleks.auctioneer.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lot")
public class LotController {

    @Autowired
    private LotService lotService;

    @PostMapping("/addlots")
    public ResponseEntity<String> uploadLot(@RequestParam LotDTO lotDTO,
                                            @RequestParam("file") MultipartFile file,
                                            @RequestParam("previewImage") MultipartFile previewImage)  {
        return lotService.uploadLot(lotDTO, file, previewImage);
    }



}
