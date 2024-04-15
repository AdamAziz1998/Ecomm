package com.azizONeill.userservice.request;

import com.azizONeill.userservice.model.UserDetails;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class UserUpdateRequest {
    @NotBlank(message = "Id is required")
    private String id;
    private String username;
    private String password;
    private UserDetails userDetails;
}
