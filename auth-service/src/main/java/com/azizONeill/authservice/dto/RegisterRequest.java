package com.azizONeill.authservice.dto;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private String username;
    private String password;
}