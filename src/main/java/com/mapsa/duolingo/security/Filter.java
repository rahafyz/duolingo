/*
package com.mapsa.duolingo.security;

import com.mapsa.duolingo.exception.CustomException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Filter extends OncePerRequestFilter {

    private JwtBuilder jwtBuilder;

    public Filter(JwtBuilder jwtBuilder) {
        this.jwtBuilder = jwtBuilder;
    }

    public Filter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtBuilder.resolveToken(request);
        try {
            if(token != null && jwtBuilder.validateToken(token)
                && jwtBuilder.getUserToken(token))
                filterChain.doFilter(request,response);
        }catch (CustomException ex) {
            throw new CustomException(ex.getMessage(),ex.getHttpStatus());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return "/user/login".equals(path);
    }
}

*/
