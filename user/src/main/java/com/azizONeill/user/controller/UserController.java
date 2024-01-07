package com.azizONeill.user.controller;

import com.azizONeill.user.dto.NewUserRequestDTO;
import com.azizONeill.user.dto.UpdateUserRequestDTO;
import com.azizONeill.user.dto.UserDTO;
import com.azizONeill.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@Tag(name="Users API")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @Operation(summary = "Retrieve All Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found zero or more Users",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))) }),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<?> getAllUsers() {

        log.info("getUsers started");

        List<UserDTO> users = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Retrieve User by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User matching this Id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "User Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<?> getUserById(@Parameter(description = "id of User to be found") @PathVariable UUID id) {

        log.info("getUserById started");

        UserDTO userDTO = userService.getUserById(id);

        if (userDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @GetMapping("/usersByEmail/{email}")
    @Operation(summary = "Retrieve User by Email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User matching this Email",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "User Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<?> getUserByEmail(@Parameter(description = "email of User to be found") @PathVariable String email) {

        log.info("getUserByEmail started");

        UserDTO userDTO = userService.getUserByEmail(email);

        if (userDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @PostMapping("/users")
    @Operation(summary = "Create New User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Created new User",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Failed to Create new User",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<?> createUser(@Parameter(description = "New User Body Content to be created") @Valid @RequestBody NewUserRequestDTO newUserDTO) {

        log.info("createUser newUserDTO: " + newUserDTO);

        UserDTO userDTO = userService.createUser(newUserDTO);

        log.info("createUser Completed");

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated User",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Failed to Update User",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User Id does not exist",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<?> updateUser(
            @Parameter(description = "User Id to be updated") @PathVariable UUID id,
            @Parameter(description = "User Elements/Body Content to be updated") @Valid @RequestBody UpdateUserRequestDTO updatedUserDTO) {

        log.info("updateUser started");

        UserDTO userDTO = userService.updateUser(id, updatedUserDTO);

        if (userDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted User",
                    content = { @Content() }),
            @ApiResponse(responseCode = "400", description = "Failed to Delete User",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User Id does not exist",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization Failed",
                    content = @Content) })
    public ResponseEntity<?> deleteUser(@Parameter(description = "User Id to be deleted") @PathVariable UUID id) {
        // Delete the id

        log.info("deleteUser Started");

        UserDTO userDTO = userService.deleteUserById(id);

        if (userDTO!= null) {
            return ResponseEntity.status(HttpStatus.OK).body("{}");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
