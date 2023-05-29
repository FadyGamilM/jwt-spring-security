package com.fady.jwt_security.security.jwt;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String email;

        // 1. get the jwt token and check its exists
        final String authHeader = request.getHeader("Authorization");

        // 2. checks that token exists
        var isTokenExists = CheckIfJwtTokenExists(authHeader);
        if (!isTokenExists)
            filterChain.doFilter(request, response);

        // 2. extract the token from the header
        final String jwt = ExtractTokenFromAuthorizationHeader(authHeader);

        // 3. call the jwt-service to extract the username from the token

        // 4. call the userDetailsService to check if we have this user in our db or not
    }

    private boolean CheckIfJwtTokenExists(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // delegate to the next filter because the jwt-filter doesn't have to perform
            // the rest of the work
            return false;
        } else {
            return true;
        }
    }

    private String ExtractTokenFromAuthorizationHeader(String authHeader) {
        return authHeader.substring(7);
    }

}