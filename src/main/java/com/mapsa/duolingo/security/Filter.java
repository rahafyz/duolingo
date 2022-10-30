package com.mapsa.duolingo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class Filter extends OncePerRequestFilter {


    private final JwtBuilder jwtTokenUtil;
    private final AntPathMatcher pathMatcher;

    UserDetail u;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtTokenUtil.validateToken(requestTokenHeader)) {
            try {
                Claims body = jwtTokenUtil.getAllClaimsFromToken(requestTokenHeader);

                u.setUsername(body.getSubject());
                u.setUserId(Long.parseLong((String) body.get("userId")));


            } catch (JwtException | ClassCastException e) {

            }

            chain.doFilter(request, response);
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return "/user/login".equals(path)|| "/user/verification/*".equals(path) || "/user/user".equals(path)|| pathMatcher.match("/swagger-ui/**",path);
    }
}

