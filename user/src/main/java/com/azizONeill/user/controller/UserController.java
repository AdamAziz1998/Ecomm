package com.azizONeill.user.controller;

import com.azizONeill.user.dto.NewUserRequestDTO;
import com.azizONeill.user.dto.UpdateUserRequestDTO;
import com.azizONeill.user.dto.UserDTO;
import com.azizONeill.user.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers() {

        List<UserDTO> users = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {

        UserDTO userDTO = userService.getUserById(id);

        if (userDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @GetMapping("/user/email/{emailAddress}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String emailAddress) {

        UserDTO userDTO = userService.getUserByEmail(emailAddress);

        if (userDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody NewUserRequestDTO newUserDTO) {

        UserDTO userDTO = userService.createUser(newUserDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @Valid @RequestBody UpdateUserRequestDTO updatedUserDTO) {

        UserDTO userDTO = userService.updateUser(id, updatedUserDTO);

        if (userDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        // Delete the id

        UserDTO userDTO = userService.deleteUserById(id);

        if (userDTO!= null) {
            return ResponseEntity.status(HttpStatus.OK).body("{}");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
        }
    }
}
