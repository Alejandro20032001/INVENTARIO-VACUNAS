package com.kruger.pruebatecnica.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kruger.pruebatecnica.auth.service.UserDetailImpl;
import com.kruger.pruebatecnica.auth.security.Credentials;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)  {
        Credentials authCredentials =new Credentials();
        try {
            //System.out.println("esta intentando");
            authCredentials = new ObjectMapper().readValue(
                    request.getReader(), Credentials.class
            );
        } catch (Exception e){
        }

        try {
            UsernamePasswordAuthenticationToken PAToken =
                    new UsernamePasswordAuthenticationToken(
                            authCredentials.getUsername(),
                            authCredentials.getContrasena(),
                            Collections.emptyList());
            System.out.println("USERNAME: "+authCredentials.getUsername());
            System.out.println("PASSWORD: "+authCredentials.getContrasena());
            return getAuthenticationManager().authenticate(PAToken);
        }catch (
                AuthenticationException e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication authentication
    ) throws IOException {
        System.out.println("ENTRA AL SUCCESS ");
        UserDetailImpl userDetails= (UserDetailImpl) authentication.getPrincipal();
        String token = JwtTokenUtils.createToken(userDetails);
        System.out.println("token: "+token);
        response.addHeader("Authorization", "Bearer "+ token);
        response.getWriter().flush();
    }
}
