package com.azizONeill.user.dto;

import com.azizONeill.user.model.enums.Role;
import lombok.Data;

@Data
public class AuthUserDto {
    private String id;
    private String username;
    private String password;
    private Role role;
}