package com.eleks.auctioneer.controller;


import com.eleks.auctioneer.DTO.LotDTO;
import com.eleks.auctioneer.service.LotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/lots")
public class LotController {

    @Autowired
    private LotService lotService;
    @Autowired
    private ObjectMapper objectMapper;
    @PostMapping("/add")
    public ResponseEntity<String> uploadLot(@RequestParam ("model") String model,
                                            @RequestParam("file") MultipartFile file,
                                            @RequestParam("previewImage") MultipartFile previewImage) throws IOException {

        var lotDTO = objectMapper.readValue(model, LotDTO.class);
        return lotService.uploadLot(lotDTO, file, previewImage);
    }

    @GetMapping("/active")
    public List<LotDTO> getActiveLots()
    {
        return lotService.getAllActiveLots();
    }
}
