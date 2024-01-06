package com.example.contentencoding;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Service
public class YourService {

    private final RestTemplate restTemplate;

    public YourService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String,Object> callThirdPartyAPI() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Encoding", "gzip"); // Set your desired encoding here

        // You can also set other headers if needed
        // headers.set("Accept", "application/json");

        HttpEntity<Map<String,Object>> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts",
                HttpMethod.GET, // Change to your desired HTTP method
                requestEntity,
                Map.class
        );

        // Process the response as needed
        Map<String,Object> responseBody = responseEntity.getBody();
        return  responseBody    ;
        // Handle the response...
    }
}
