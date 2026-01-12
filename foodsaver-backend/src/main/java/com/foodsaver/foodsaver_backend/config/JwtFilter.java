package com.foodsaver.foodsaver_backend.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();
        String method = req.getMethod();

        // ✅ Allow auth + preflight
        if (path.startsWith("/api/auth") || method.equalsIgnoreCase("OPTIONS")) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = req.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Missing token");
            return;
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        // 🔑 EXTRACT DETAILS
        String email = jwtUtil.extractClaims(token).getSubject();
        String role  = jwtUtil.extractClaims(token).get("role", String.class);

        // 🔥 VERY IMPORTANT: ROLE_ prefix
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_" + role);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        List.of(authority)
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
