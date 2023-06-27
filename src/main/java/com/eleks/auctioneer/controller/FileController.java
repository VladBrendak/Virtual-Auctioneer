package com.eleks.auctioneer.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;

@RestController
public class FileController {
    @RequestMapping(value = "files/{fileReference}", method = RequestMethod.GET)
    public ResponseEntity<StreamingResponseBody> getStreamingFile(@PathVariable("fileReference") String fileReference, HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"demo.pdf\"");
            InputStream inputStream = new FileInputStream(new File("D:\\КН-319\\PracticeJava\\" + fileReference + ".pdf"));

            StreamingResponseBody responseBody = outputStream -> {
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    System.out.println("Writing some bytes..");
                    outputStream.write(data, 0, nRead);
                }
            };

            return ResponseEntity.ok(responseBody);
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
