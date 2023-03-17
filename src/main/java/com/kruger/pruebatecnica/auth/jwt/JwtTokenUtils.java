package com.kruger.pruebatecnica.auth.jwt;


import com.kruger.pruebatecnica.auth.model.entity.Rol;
import com.kruger.pruebatecnica.auth.model.entity.User;
import com.kruger.pruebatecnica.auth.service.UserDetailImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JwtTokenUtils {

    private static final long EXPIRE_TIME_DURATION = 2 * 60 * 60* 1000; //2horas.
    private static final String ACCESS_TOKEN_SECRET= "abcdefghijklmnOPQRSTUVWXYZ";

    public static String createToken(UserDetailImpl user){
        Date fechaExpiracion =new Date(System.currentTimeMillis() + EXPIRE_TIME_DURATION);

        return Jwts.builder().setSubject(user.getUsername())
                .setExpiration(fechaExpiracion)
                .claim("roles", user.getRols())
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }



    public static UserDetails getUserDetails(String token){
        User userDetails =new User();
        Claims claims = getClaims(token);
        String subject =claims.getSubject();
        String roles = (String) claims.get("roles");

        roles = roles.replace("[", "").replace("]", "");
        String[] roleNames = roles.split(",");

        for (String aRoleName : roleNames) {

            aRoleName = aRoleName.trim();
            System.out.println("rol actual: "+ aRoleName);
            Rol rol = new Rol();
            rol.setRolName(aRoleName);
            userDetails.addRol(rol);

        }

        return userDetails;
    }

    public static Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build().parseClaimsJws(token).getBody();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build().parseClaimsJws(token).getBody();

            UserDetails userDetails = getUserDetails(token);

            return new UsernamePasswordAuthenticationToken(
                    userDetails, null,userDetails.getAuthorities()
            );
        }catch (Exception e){
            return null;
        }
    }
}
