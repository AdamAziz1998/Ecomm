package com.azizONeill.user.dto;

import com.azizONeill.user.model.UserDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String id;
    private String username;
    private UserDetails userDetails;
}
