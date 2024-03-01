package com.takaibun.plexmetadatamanager.security;

import com.takaibun.plexmetadatamanager.service.UserService;
import com.takaibun.plexmetadatamanager.service.impl.JwtTokenAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class JwtTokenOncePerRequestFilter extends OncePerRequestFilter {
    private final UserService userService;

    public JwtTokenOncePerRequestFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Optional<String> tokenOpt = getTokenByRequest(request);
            if (tokenOpt.isPresent() && userService.validateToken(tokenOpt.get())) {

                Authentication auth =  new JwtTokenAuthentication(null);

                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication!", e);
        }
        filterChain.doFilter(request, response);
    }

    private Optional<String> getTokenByRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }
}
