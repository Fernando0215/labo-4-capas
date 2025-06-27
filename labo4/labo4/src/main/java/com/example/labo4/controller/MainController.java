package com.example.labo4.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @GetMapping(path = "/")
    public ResponseEntity<?> index() {
        
        OAuth2User user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("Usuario autenticado: " + user.getName());
        System.out.println("Atributos: " + user.getAttributes());

        Map<String, String> body = new HashMap<>();
        body.put("name", user.getName());

        return new ResponseEntity<>(body, HttpStatus.OK);
    }


    @GetMapping(path = "/unauthenticated")
    public ResponseEntity<?> unauthenticatedRequests() {
        Map<String, String> body = new HashMap<>();
        body.put("name", "unknown");

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
