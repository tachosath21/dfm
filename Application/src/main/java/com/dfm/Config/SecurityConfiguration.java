package com.dfm.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration
{
    private final JwtAuthenticationFilter jwt_authentication_filter;
    private final AuthenticationProvider authentication_provider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http_security) throws Exception
    {
        http_security
            .authorizeHttpRequests()
            .requestMatchers("/api/Users/**")
            .permitAll()
            .requestMatchers(HttpMethod.GET, "/api/Estates/**")
            .hasAnyAuthority("ROLE_ADMIN", "ROLE_NOTARY", "ROLE_SELLER", "ROLE_BUYER")
            .requestMatchers(HttpMethod.POST, "/api/Estates/**")
            .hasAnyAuthority("ROLE_ADMIN", "ROLE_SELLER")
            .requestMatchers(HttpMethod.PUT, "/api/Estates/**")
            .hasAnyAuthority("ROLE_ADMIN", "ROLE_SELLER")
            .requestMatchers(HttpMethod.DELETE, "/api/Estates/**")
            .hasAnyAuthority("ROLE_ADMIN", "ROLE_SELLER")
            .requestMatchers(HttpMethod.GET, "/api/Transfers/**")
            .hasAnyAuthority("ROLE_ADMIN", "ROLE_NOTARY", "ROLE_SELLER", "ROLE_BUYER")
            .requestMatchers(HttpMethod.POST, "/api/Transfers/**")
            .hasAnyAuthority("ROLE_ADMIN", "ROLE_NOTARY")
            .requestMatchers(HttpMethod.PUT, "/api/Transfers/**")
            .hasAnyAuthority("ROLE_ADMIN", "ROLE_NOTARY", "ROLE_SELLER", "ROLE_BUYER")
            .requestMatchers(HttpMethod.DELETE, "/api/Transfers/**")
            .hasAnyAuthority("ROLE_ADMIN", "ROLE_NOTARY")
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(this.authentication_provider)
            .addFilterBefore(this.jwt_authentication_filter, UsernamePasswordAuthenticationFilter.class)
            .csrf()
            .disable()
            .cors();

        return http_security.build();
    }
}
