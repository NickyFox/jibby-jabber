package com.jibbyjabber.security;

import com.jibbyjabber.model.dto.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private final String secret;

    public JwtTokenUtil(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    /**
     * Retrieves the username from the token.
     */
    public String getEmailFromToken(String token) {
        Claims claims = getClaimsForToken(token);
        return claims.get("email").toString();
    }

    /**
     * Retrieves the clientId from the token.
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = getClaimsForToken(token);
        return Long.valueOf(claims.get("id").toString());
    }

    /**
     * Retrieves the expiration date from the token.
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsForToken(token).getExpiration();
    }

    /**
     * Retrieves all claims from the token by using the same key that was used in its creation.
     */
    public Claims getClaimsForToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(new SecretKeySpec(secret.getBytes(), "HmacSHA256"))
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks if the token has expired.
     */
    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Generates the JWT Token
     * <p>
     * 1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
     * 2. Sign the JWT using the HS512 algorithm and secret key.
     * 3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
     * compaction of the JWT to a URL-safe string
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final long jwtTokenValidity = 1000 * 60 * 24; // a minute in milliseconds
        final long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtTokenValidity)) // now plus a jwtValidity in milliseconds
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    /**
     * Validates the token by checking that it belongs to the user and it hasn't expired.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getEmailFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }


    /**
     * Generates a new token for the specified subscriber.
     */
    public String generateWebToken(UserDetails userDetails, User user) {
        final Map<String, Object> claims = makeCommonJwtInformationMapForWeb(user);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private Map<String, Object> makeCommonJwtInformationMapForWeb(User user) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        return claims;
    }
}