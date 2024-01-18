package com.binarychris.carShow.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    static final long EXPIRATIONTIME = 86400000;
    static final String PREFIX = "Bearer";
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.ES256);// won't use this, will use proper certificate on job
    public String getToken(String username){
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(key)
                .compact();
        return token;
    }
    public String getAuthUser(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null){
            String user = Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token.replace(PREFIX, ""))
                    .getPayload()
                    .getSubject();
            if (user != null) return user;
        }
        return null;
    }
}
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser()
//                .verifyWith(getPublicSigningKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }