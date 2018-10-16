package com.laestufa.laEstufa.security;

import com.laestufa.laEstufa.model.JwtUser;
import com.sun.istack.internal.logging.Logger;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private static final String ENCODED_GENERATION_CODE = "superUltraHardToBreakPasswordForDecode";

    protected final Logger log = Logger.getLogger(getClass());

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(ENCODED_GENERATION_CODE)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setUserLogin((String) body.get("userLogin"));
            jwtUser.setId((String) body.get("userId"));
            jwtUser.setRole((String) body.get("role"));
        } catch (Exception e) {
            log.warning(e.getMessage());
        }

        return jwtUser;
    }
}
