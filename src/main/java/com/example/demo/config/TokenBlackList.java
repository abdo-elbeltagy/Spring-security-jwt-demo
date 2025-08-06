package com.example.demo.config;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenBlackList {

    private Set<String> blockedList = ConcurrentHashMap.newKeySet();

    public void blackList(String token) {
        blockedList.add(token);
    }

    public boolean isBlocked(String token) {
        return blockedList.contains(token);
    }

}
