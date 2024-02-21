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

    @NotNull(message = "title cannot be null")
    private Title title;

    @NotNull(message = "firstName cannot be null")
    @NotBlank(message = "FirstName cannot be empty")
    @Size(min = 2, max = 200, message = "firstName must be between 2 and 200 characters")
    private String firstName;

    @NotNull(message = "LastName cannot be null")
    @NotBlank(message = "LastName cannot be empty")
    @Size(min = 2, max = 200, message = "lastName must be between 2 and 200 characters")
    private String lastName;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be empty")
    @EmailAddress(message = "Please provide a valid email address")
    private String email;

    @NotNull(message = "phoneNumber cannot be null")
    @NotBlank(message = "phoneNumber cannot be empty")
    @UKPhoneNumber(message = "Please provide a valid UK phone number")
    private String phoneNumber;

    @NotNull(message = "houseNameNumber cannot be null")
    @NotBlank(message = "houseNameNumber cannot be empty")
    private String houseNameNumber;

    @NotNull(message = "addressLine1 cannot be null")
    @NotBlank(message = "addressLine1 cannot be empty")
    private String addressLine1;

    @NotNull(message = "addressLine2 cannot be null")
    @NotBlank(message = "addressLine2 cannot be empty")
    private String addressLine2;

    @NotNull(message = "townCity cannot be null")
    @NotBlank(message = "townCity cannot be empty")
    private String townCity;

    @NotNull(message = "county cannot be null")
    @NotBlank(message = "county cannot be empty")
    private String county;

    @NotNull(message = "postCode cannot be null")
    @NotBlank(message = "postCode cannot be empty")
    @UKPostCode(message = "Please provide a valid UK postcode")
    private String postCode;

}

