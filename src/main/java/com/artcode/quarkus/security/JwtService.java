package com.artcode.quarkus.security;

import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtService {

	public String generateToken(String username, Set<String> roles) {
        return Jwt.issuer("https://your-app.com/issuer")
                  .subject(username)
                  .audience("example-audience")
                  .groups(roles)
                  .claim("email", username)
                  .expiresAt(System.currentTimeMillis() / 1000 + 172800000)
                  .sign(); // Uses HS256 & your configured secret
    }
}
