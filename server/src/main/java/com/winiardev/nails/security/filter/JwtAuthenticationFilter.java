package com.winiardev.nails.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.winiardev.nails.security.service.IServiceJwt;
import com.winiardev.nails.security.service.impl.ImplServiceUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

import java.io.IOException;

/**
 * A filter that handles JWT authentication for incoming requests.
 * <p>
 * This filter extracts the JWT from the "Authorization" header, validates it,
 * and sets the authentication in the Spring Security context if the token is
 * valid.
 * If the token is expired, invalid, or if no token is present, it returns an
 * appropriate
 * HTTP error response.
 * </p>
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final IServiceJwt jwtService;
    private final ImplServiceUserDetails userDetailsService;

    /**
     * Processes the request to extract and validate the JWT token.
     * <p>
     * If the token is valid, the filter sets the authentication in the
     * SecurityContext.
     * If the token is expired, invalid, or if the user associated with the token
     * cannot be found,
     * the filter returns a 401 Unauthorized response with an appropriate message.
     * </p>
     *
     * @param request     the HTTP request
     * @param response    the HTTP response
     * @param filterChain the filter chain to pass control to the next filter
     * @throws ServletException if an error occurs during the filtering
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String email;
        try {
            // Attempt to extract the email from the token
            email = jwtService.extractEmail(token);
        } catch (ExpiredJwtException ex) {
            // Token is expired - return 401 Unauthorized
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(ex.getMessage());
            return;
        } catch (JwtException ex) {
            // Another issue with the token - return 401 Unauthorized
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(ex.getMessage());
            return;
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails;

            // Handle case where the user might have been deleted while the token is still
            // valid.
            // Prevents a 500 server error by returning a 401 Unauthorized instead.
            try {
                userDetails = userDetailsService.loadUserByUsername(email);
            } catch (UsernameNotFoundException ex) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(ex.getMessage());
                return;
            }

            if (jwtService.isValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
