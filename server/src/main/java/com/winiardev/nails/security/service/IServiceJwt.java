package com.winiardev.nails.security.service;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;

import com.winiardev.nails.account.embeddable.Email;
import com.winiardev.nails.account.entity.Account;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.function.Function;

/**
 * Interface for JWT service, providing methods for token generation,
 * validation, and extraction.
 * 
 * @author winiar.dev
 */
public interface IServiceJwt {

    /**
     * Generates a JWT token for the given account.
     *
     * @param account the account for which the token is to be generated.
     * @return the generated JWT token.
     */
    String generateToken(Account account);

    /**
     * Returns the secret key used for signing JWT tokens.
     *
     * @return the secret key, decoded from BASE64.
     */
    SecretKey getSignKey();

    /**
     * Extracts the payload (claims) from a JWT token after verifying it with the
     * signing key.
     *
     * @param token the JWT token from which to extract the claims.
     * @return the claims extracted from the token.
     * @throws TokenExpiredException if the token is expired.
     */
    Claims extractPayload(String token);

    /**
     * Extracts data from a JWT token using a provided resolver function.
     *
     * @param token    the JWT token from which to extract data.
     * @param resolver the function that resolves a Claims object into a value of
     *                 type T.
     * @param <T>      the type of the value returned by the resolver.
     * @return the resolved value of type T.
     */
    <T> T extract(String token, Function<Claims, T> resolver);

    /**
     * Extracts the expiration date from a JWT token.
     *
     * @param token the JWT token from which to extract the expiration date.
     * @return the expiration date of the token.
     */
    Date extractExpirationDate(String token);

    /**
     * Extracts the email (subject) from a JWT token.
     *
     * @param token the JWT token from which to extract the email.
     * @return the email (subject) extracted from the token.
     */
    String extractEmail(String token);

    /**
     * Validates a JWT token against the given user details.
     *
     * @param token       the JWT token to validate.
     * @param userDetails the user details to validate the token against.
     * @return true if the token is valid and not expired, false otherwise.
     */
    Boolean isValid(String token, UserDetails userDetails);

    /**
     * Checks if a JWT token is expired.
     *
     * @param token the JWT token to check.
     * @return true if the token is expired, false otherwise.
     */
    Boolean isTokenExpired(String token);

    Email extractEmailFromServlet(HttpServletRequest servlet);
}
