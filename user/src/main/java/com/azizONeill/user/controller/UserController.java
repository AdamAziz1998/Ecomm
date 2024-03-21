package com.azizONeill.user.controller;

import com.azizONeill.user.dto.NewUserRequestDTO;
import com.azizONeill.user.dto.UpdateUserRequestDTO;
import com.azizONeill.user.dto.UserDTO;
import com.azizONeill.user.service.UserService;
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
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @GetMapping("/user/email/{emailAddress}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String emailAddress) {
        UserDTO userDTO = userService.getUserByEmail(emailAddress);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody NewUserRequestDTO newUserDTO) {
        UserDTO userDTO = userService.createUser(newUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UpdateUserRequestDTO updatedUserDTO) {
        UserDTO userDTO = userService.updateUser(id, updatedUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable UUID id) {
        UserDTO userDTO = userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
}
