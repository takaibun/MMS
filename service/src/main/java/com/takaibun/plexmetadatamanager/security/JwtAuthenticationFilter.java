package com.takaibun.plexmetadatamanager.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Jwt认证拦截器
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Component
public class JwtAuthenticationFilter implements Filter, Ordered {

    private final String[] allowedEndpoints = new String[]{"/login", "/logout", "/actuator/**", "/webhook"};
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        for (String endpoint : allowedEndpoints) {
            if (requestURI.endsWith(endpoint)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        String token = getTokenFromRequest(request);
        if (jwtTokenProvider.validateToken(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
