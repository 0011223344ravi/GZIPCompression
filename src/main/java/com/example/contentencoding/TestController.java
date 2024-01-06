package com.example.contentencoding;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public void test(HttpServletRequest request, HttpServletResponse reponse) throws IOException {

        // Response Body
        String content = "Hello Spring Cloud";

        // Encoding types supported by the client
        String acceptEncooding = request.getHeader(HttpHeaders.ACCEPT_ENCODING);

        // It is assumed that the client only supports the GZIP encoding type.
        acceptEncooding = "gzip";

        // The response body is encoded using gzip
        reponse.setHeader(HttpHeaders.CONTENT_ENCODING, acceptEncooding);
        reponse.setContentType(MediaType.TEXT_PLAIN_VALUE);
        reponse.setCharacterEncoding(StandardCharsets.UTF_8.displayName());

        // Response to the client after compression using Gzip
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(reponse.getOutputStream())) {
            gzipOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
            gzipOutputStream.finish();
        }
    }
}
