package com.azizONeill.user.dto;


import com.azizONeill.user.model.enums.Title;
import com.azizONeill.user.validation.EmailAddress;
import com.azizONeill.user.validation.UKPhoneNumber;
import com.azizONeill.user.validation.UKPostCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequestDTO {

    @Valid

    @NotNull(message = "title is mandatory")
    @NotBlank(message = "title is mandatory")
    private Title title;

    @NotNull(message = "firstName is mandatory")
    @NotBlank(message = "FirstName is mandatory")
    @Size(min = 2, max = 200, message = "firstName must be between 2 and 200 characters")
    private String firstName;

    @NotNull(message = "LastName is mandatory")
    @NotBlank(message = "LastName is mandatory")
    @Size(min = 2, max = 200, message = "lastName must be between 2 and 200 characters")
    private String lastName;

    @NotNull(message = "email is mandatory")
    @NotBlank(message = "email is mandatory")
    @EmailAddress(message = "Please provide a valid email address")
    private String email;

    @NotNull(message = "phoneNumber is mandatory")
    @NotBlank(message = "phoneNumber is mandatory")
    @UKPhoneNumber(message = "Please provide a valid UK phone number")
    private String phoneNumber;

    @NotNull(message = "houseNameNumber is mandatory")
    @NotBlank(message = "houseNameNumber is mandatory")
    private String houseNameNumber;

    @NotNull(message = "addressLine1 is mandatory")
    @NotBlank(message = "addressLine1 is mandatory")
    private String addressLine1;

    @NotNull(message = "addressLine2 is mandatory")
    @NotBlank(message = "addressLine2 is mandatory")
    private String addressLine2;

    @NotNull(message = "townCity is mandatory")
    @NotBlank(message = "townCity is mandatory")
    private String townCity;

    @NotNull(message = "county is mandatory")
    @NotBlank(message = "county is mandatory")
    private String county;

    @NotNull(message = "postCode is mandatory")
    @NotBlank(message = "postCode is mandatory")
    @UKPostCode(message = "Please provide a valid UK postcode")
    private String postCode;

}

