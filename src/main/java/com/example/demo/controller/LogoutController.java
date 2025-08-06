package com.example.demo.controller;

import com.example.demo.config.TokenBlackList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LogoutController {
    @Autowired
    TokenBlackList tokenBlackList;

    @PostMapping("/logut")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String Authtoken) {
        String token = Authtoken.replace("Bearer ", "");
        System.out.println("user logged out: " + token);
        tokenBlackList.blackList(token);
        return ResponseEntity.ok("user logged out successfully" + token);
    }
}
