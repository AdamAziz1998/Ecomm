package com.azizONeill.userservice.request;

import com.azizONeill.userservice.model.UserDetails;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;


@Data
public class UserUpdateRequest {
    private UUID id;
    private String email;
    private String password;
    private UserDetails userDetails;
}
