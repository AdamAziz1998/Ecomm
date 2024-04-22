package com.azizONeill.user.dto;

import com.azizONeill.user.model.UserDetails;
import lombok.Data;

import java.util.UUID;


@Data
public class UserUpdateRequest {
    private UUID id;
    private String email;
    private String password;
    private UserDetails userDetails;
}
