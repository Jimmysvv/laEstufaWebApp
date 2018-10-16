package com.laestufa.laEstufa.security;

import com.laestufa.laEstufa.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {

    private static final String ENCODED_GENERATION_CODE = "superUltraHardToBreakPasswordForDecode";

    public String generate(JwtUser jwtUser) {

        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userLogin", jwtUser.getUserLogin());
        claims.put("userId", jwtUser.getId());
        claims.put("role", jwtUser.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, ENCODED_GENERATION_CODE)
                .compact();
    }
}
