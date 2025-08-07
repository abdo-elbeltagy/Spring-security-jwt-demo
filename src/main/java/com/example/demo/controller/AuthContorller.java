package com.example.demo.controller;

import com.example.demo.config.TokenBlackList;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthRes;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthContorller {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    TokenBlackList tokenBlackList;

    private final long accessTokenTime = 1000 * 60 * 15;
    private final long refreshTokenTime = 1000L * 60 * 60 * 24 * 7;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        System.out.println("here can't authenticate");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String accessToken = jwtUtil.generateAccessToken(userDetails, accessTokenTime);
        String refreshToken = jwtUtil.generateAccessToken(userDetails, refreshTokenTime);
        return ResponseEntity.ok().body(new AuthRes(accessToken, refreshToken));
    }

    @PostMapping("/logut")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String Authtoken) {
        String token = Authtoken.replace("Bearer ", "");
        System.out.println("user logged out: " + token);
        tokenBlackList.blackList(token);
        return ResponseEntity.ok("user logged out successfully" + token);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthRes> refresh(@RequestHeader("Refresh") String refreshToken) {
        String userName = jwtUtil.extractName(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if (jwtUtil.validateToken(refreshToken, userDetails)) {
            String accessToken = jwtUtil.generateAccessToken(userDetails, accessTokenTime);
            return ResponseEntity.ok(new AuthRes(accessToken, refreshToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
