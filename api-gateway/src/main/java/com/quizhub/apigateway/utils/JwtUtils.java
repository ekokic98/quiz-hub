package com.quizhub.apigateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils implements Serializable {

    private static int jwtExpirationMilliseconds;
    private static String jwtSecret;

    @Value("${app.jwtExpiration}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        JwtUtils.jwtExpirationMilliseconds = jwtExpirationInMs;
    }

    @Value("${app.jwtSecret}")
    public void setJwtSecret(String jwtSecret) {
        JwtUtils.jwtSecret = jwtSecret;
    }

    public static int getJwtExpirationMilliseconds() {
        return jwtExpirationMilliseconds;
    }

    public static void setJwtExpirationMilliseconds(int jwtExpirationMilliseconds) {
        JwtUtils.jwtExpirationMilliseconds = jwtExpirationMilliseconds;
    }

    public static String getJwtSecret() {
        return jwtSecret;
    }
}
