package com.example.contentencoding;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

@RestController
public class TestController1 {

    @GetMapping("/gzip")
    public ResponseEntity<String> getName() {
        String str  = "This is a simple string response.compressed";

        // HttpHeaders headers = new HttpHeaders();
        //headers.add(HttpHeaders.CONTENT_ENCODING, "application/gzip"); // Set content encoding to gzip

        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @PostMapping("/your-endpoint1")
    public ResponseEntity<String> yourEndpoint1(@RequestBody String requestBody) {
      //  byte[] compressedBody = compress(requestBody.getBytes());



        return ResponseEntity
                .status(HttpStatus.OK)
                .body(requestBody);
    }

    @PostMapping("/your-endpoint")
    public ResponseEntity<byte[]> yourEndpoint(@RequestBody String requestBody) {
        byte[] compressedBody = compress(requestBody.getBytes());

        HttpHeaders headers = new HttpHeaders();
      //  headers.set(HttpHeaders.CONTENT_ENCODING, "gzip");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(compressedBody);
    }

    // Method to compress byte array using GZIP
    private byte[] compress(byte[] data) {
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
             GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream)) {

            gzipStream.write(data);
            gzipStream.finish();

            return byteStream.toByteArray();
        } catch (IOException e) {
            // Handle compression failure
            e.printStackTrace();
            return new byte[0];
        }
    }
}
