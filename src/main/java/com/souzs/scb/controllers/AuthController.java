package com.souzs.scb.controllers;

import com.souzs.scb.domain.dtos.UserLoginDTO;
import com.souzs.scb.domain.payloads.TokenCookies;
import com.souzs.scb.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("signin")
    public ResponseEntity<?> signin(@Valid @RequestBody UserLoginDTO dto) {
        TokenCookies cookies = authService.signin(dto);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookies.getAccessToken().toString())
                .header(HttpHeaders.SET_COOKIE, cookies.getRefreshToken().toString())
                .build();
    }

    @PostMapping("refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        TokenCookies cookies = authService.refreshToken(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookies.getAccessToken().toString())
                .build();
    }

    @PostMapping("signout")
    public ResponseEntity<?> signout(HttpServletRequest request) {
        TokenCookies cookies = authService.signout(request);


        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookies.getAccessToken().toString())
                .header(HttpHeaders.SET_COOKIE, cookies.getRefreshToken().toString())
                .build();
    }
}
