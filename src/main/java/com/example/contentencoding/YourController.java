package com.example.contentencoding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class YourController {

    @Autowired
    private YourService yourServices;

    @GetMapping("/")
    Map<String,Object> getPosts(){
        return yourServices.callThirdPartyAPI();
    }

}
