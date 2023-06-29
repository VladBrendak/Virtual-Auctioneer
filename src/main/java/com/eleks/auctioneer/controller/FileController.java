package com.eleks.auctioneer.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/lots")
public class FileController {
    @Value("${spring.storage.file-path}")
    private String filePath;

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
