package com.kruger.pruebatecnica.auth.security;

import com.kruger.pruebatecnica.auth.jwt.JwtAuthenticationFilter;
import com.kruger.pruebatecnica.auth.jwt.JwtAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    private UserDetailsService userDetailsService;
    private JwtAuthorizationFilter jwtAuthorizationFilter;


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager manager)
            throws Exception{
        JwtAuthenticationFilter jwtAuthenticationFilter =new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(manager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return http.cors().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/testing/otro2", "/login").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http,
                                                PasswordEncoder passwordEncoder) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
                .and().build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
