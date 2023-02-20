package com.dfm.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dfm.Users.IUsersRepository;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final JwtService jwt_service;
    private final IUsersRepository users_repository;

    @Override
    protected void doFilterInternal(HttpServletRequest http_servlet_request, HttpServletResponse http_servlet_response, FilterChain filter_chain) throws ServletException, IOException
    {
        final String header = http_servlet_request.getHeader("Authorization");
        final String jwt;
        final String email;

        if (header == null || !header.startsWith("Bearer "))
        {
            filter_chain.doFilter(http_servlet_request, http_servlet_response);
            return;
        }

        jwt = header.substring(7);
        email = this.jwt_service.extractUsername(jwt);

        if (email == null || SecurityContextHolder.getContext().getAuthentication() != null)
        {
            filter_chain.doFilter(http_servlet_request, http_servlet_response);
            return;
        
        }

        var user_model_optional = this.users_repository.findByEmail(email);

        if (!user_model_optional.isPresent())
        {
            filter_chain.doFilter(http_servlet_request, http_servlet_response);
            return;
        }

        var user_model = user_model_optional.get();

        if (!this.jwt_service.isTokenValid(jwt, user_model))
        {
            filter_chain.doFilter(http_servlet_request, http_servlet_response);
            return;
        }

        var token = new UsernamePasswordAuthenticationToken(user_model, null, user_model.getAuthorities());
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(http_servlet_request));

        SecurityContextHolder.getContext().setAuthentication(token);

        filter_chain.doFilter(http_servlet_request, http_servlet_response);
    }
}
