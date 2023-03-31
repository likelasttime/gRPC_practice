package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipClient membershipClient;

    @GetMapping("/profile/{id}")
    public ResponseEntity<Map<String, String>> getProfile(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(membershipClient.findProfile(id));
    }
}
