package com.pos.auth_service.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
  private final Key key;
  private final long expMs;
  public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration-ms}") long expMs) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
    this.expMs = expMs;
  }

  public String generateToken(String username, Set<String> roles) {
    return Jwts.builder()
      .setSubject(username)
      .claim("roles", roles.stream().collect(Collectors.joining(",")))
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + expMs))
      .signWith(key)
      .compact();
  }

  public Jws<Claims> parse(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
  }
}
