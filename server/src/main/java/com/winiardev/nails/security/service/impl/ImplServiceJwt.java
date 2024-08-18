package com.winiardev.nails.security.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.winiardev.nails.account.embeddable.Email;
import com.winiardev.nails.account.entity.Account;
import com.winiardev.nails.security.service.IServiceJwt;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class ImplServiceJwt implements IServiceJwt {
    // 900000
    private final long TOKEN_DURATION = 900000;
    private final String SECRET_KEY = "d977816996de2deaf91666d3d943690b1265d33f054f5bf6ce149ac7e69c5f26";

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateToken(Account account) {
        return Jwts
                .builder()
                .subject(account.getEmail().email())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_DURATION))
                .signWith(getSignKey())
                .compact();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64URL
                .decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Claims extractPayload(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T extract(String token, Function<Claims, T> resolver) {
        Claims claims = extractPayload(token);
        return resolver.apply(claims);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date extractExpirationDate(String token) {
        return extract(token, Claims::getExpiration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String extractEmail(String token) {
        return extract(token, Claims::getSubject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isValid(String token, UserDetails userDetails) {
        String email = extractEmail(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isTokenExpired(String token) {
        return extractExpirationDate(token)
                .before(new Date());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Email extractEmailFromServlet(HttpServletRequest servlet) {
        if (servlet == null)
            throw new IllegalArgumentException("Servlet null during extract email");

        String authHeader = servlet.getHeader("Authorization");

        if (authHeader.isBlank())
            throw new IllegalArgumentException("AuthHeader is empty");

        String token = authHeader.substring(7);

        return new Email(extractEmail(token));
    }
}
