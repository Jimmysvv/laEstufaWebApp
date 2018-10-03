package com.laestufa.laEstufa.security;

import com.laestufa.laEstufa.model.JwtUser;
import com.sun.istack.internal.logging.Logger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secret = "siema";
    protected final Logger log = Logger.getLogger(getClass());

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setId((String) body.get("userId"));
            jwtUser.setRole((String) body.get("role"));
        } catch (Exception e) {
            log.warning(e.getMessage());
        }

        return jwtUser;
    }
}
