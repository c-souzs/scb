package com.souzs.scb.domain.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseCookie;

@Data
@AllArgsConstructor
public class TokenCookies {
    private ResponseCookie accessToken;
    private ResponseCookie refreshToken;
}
