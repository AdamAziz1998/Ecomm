package com.azizONeill.user.dto;



import com.azizONeill.user.model.enums.Title;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private Title title;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String houseNameNumber;
    private String addressLine1;
    private String addressLine2;
    private String townCity;
    private String county;
    private String postCode;
    private LocalDateTime lastLogin;
    private LocalDateTime accountCreationTime;
}
