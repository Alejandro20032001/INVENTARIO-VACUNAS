package com.kruger.pruebatecnica.auth.security;


import lombok.Data;


@Data
public class Credentials {
    private String email;
    private String contrasena;
}