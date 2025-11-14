package com.backend.app.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.backend.app.security.user.AppUserDetails;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
  
  @Value("${auth.token.jwtSecret}")
  private String jwtSecret;
  
  @Value("${auth.token.expirationInMils}")
  private int expirationTime;

  public String generateTokenForUser(Authentication authentication) {
    AppUserDetails userPrincipal = (AppUserDetails) authentication.getPrincipal();
    return Jwts.builder()
      .subject(userPrincipal.getUsername())
      .claim("id", userPrincipal.getId())
      .claim("role", userPrincipal.getAuthorities().iterator().next().getAuthority())
      .issuedAt(new Date())
      .expiration(new Date(System.currentTimeMillis() + expirationTime))
      .signWith(key(), Jwts.SIG.HS256)
      .compact();
  }

  private SecretKey key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public String getUsernameFromToken(String token) {
    return Jwts.parser()
      .verifyWith(key())
      .build()
      .parseSignedClaims(token)
      .getPayload()
      .getSubject();
  }

  public boolean validatedToken(String token) {
    try {
      Jwts
        .parser()
        .verifyWith(key())
        .build()
        .parseSignedClaims(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }
}
