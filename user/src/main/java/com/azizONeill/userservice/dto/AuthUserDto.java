package com.azizONeill.userservice.dto;

import com.azizONeill.userservice.model.enums.Role;
import lombok.Data;

@Data
public class AuthUserDto {
    private String id;
    private String username;
    private String password;
    private Role role;
}