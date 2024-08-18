package com.winiardev.nails.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.winiardev.nails.security.filter.JwtAuthenticationFilter;
import com.winiardev.nails.security.service.impl.ImplServiceUserDetails;

/**
 * Configuration class for Spring Security.
 * <p>
 * This configuration sets up the security filter chain, including disabling
 * CSRF protection,
 * setting session management to stateless, and configuring JWT authentication.
 * </p>
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final ImplServiceUserDetails userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Configures the security filter chain.
     * <p>
     * This method configures HTTP security to disable CSRF protection, disable
     * CORS, permit all requests,
     * and use stateless session management. It also adds the JWT authentication
     * filter before the
     * {@link UsernamePasswordAuthenticationFilter} to handle JWT-based
     * authentication.
     * </p>
     * 
     * @param http the HttpSecurity object used to configure the security settings
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(req -> req.anyRequest().permitAll())
                .userDetailsService(userDetailsService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Provides a {@link PasswordEncoder} bean.
     * <p>
     * This method returns an instance of {@link BCryptPasswordEncoder} for encoding
     * passwords.
     * </p>
     * 
     * @return a PasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides an {@link AuthenticationManager} bean.
     * <p>
     * This method retrieves the AuthenticationManager from the
     * AuthenticationConfiguration object.
     * </p>
     * 
     * @param configuration the AuthenticationConfiguration object used to retrieve
     *                      the AuthenticationManager
     * @return the AuthenticationManager instance
     * @throws Exception if an error occurs while retrieving the
     *                   AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
