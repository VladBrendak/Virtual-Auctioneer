package com.eleks.auctioneer.controller;

import com.eleks.auctioneer.DTO.AppUserDTO;
import com.eleks.auctioneer.DTO.LotDTO;
import com.eleks.auctioneer.entity.AppUser;
import com.eleks.auctioneer.service.LotService;
import com.eleks.auctioneer.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/lots")
public class LotController {
    @Value("${auctioneer.app.storage.file-path}")
    private String filePath;
    @Autowired
    private LotService lotService;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<String> uploadLot(@RequestParam ("model") String model,
                                            @RequestParam("file") MultipartFile file,
                                            @RequestParam("previewImage") MultipartFile previewImage,
                                            Principal principal) throws IOException {

        String currentUserEmail = principal.getName();
        AppUser appUser = userService.getUserByEmail(currentUserEmail);
        var lotDTO = objectMapper.readValue(model, LotDTO.class);
        return lotService.uploadLot(lotDTO, file, previewImage, appUser);
    }

    @GetMapping("/active")
    public List<LotDTO> getActiveLots()
    {
        return lotService.getAllActiveLots();
    }

    @RequestMapping(value = "/files/{fileReference}", method = RequestMethod.GET)
    public ResponseEntity<StreamingResponseBody> getStreamingFile(@PathVariable("fileReference") String fileReference, HttpServletResponse response) {
        try {
            Path folderPath = Paths.get(filePath);
            if (!Files.exists(folderPath)) {
                return ResponseEntity.notFound().build();
            }

            String fileExtension = "";
            try (Stream<Path> paths = Files.walk(folderPath)) {
                Optional<Path> optionalFilePath = paths
                        .filter(Files::isRegularFile)
                        .filter(path -> path.getFileName().toString().startsWith(fileReference))
                        .findFirst();
                if (optionalFilePath.isPresent()) {
                    Path filePath = optionalFilePath.get();
                    fileExtension = getFileExtension(filePath);

                    String mimeType = Files.probeContentType(filePath);
                    response.setContentType(mimeType);
                    response.setHeader("Content-Disposition", "attachment; filename=\"demo" + fileExtension + "\"");

                    InputStream inputStream = Files.newInputStream(filePath);

                    StreamingResponseBody responseBody = outputStream -> {
                        int nRead;
                        byte[] data = new byte[1024];
                        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                            System.out.println("Writing some bytes..");
                            outputStream.write(data, 0, nRead);
                        }
                    };

                    return ResponseEntity.ok(responseBody);
                }
            }

            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String getFileExtension(Path filePath) {
        String fileName = filePath.getFileName().toString();
        int dotIndex = fileName.lastIndexOf(".");
        return dotIndex == -1 ? "" : fileName.substring(dotIndex);
    }
}
