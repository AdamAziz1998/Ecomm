package com.azizONeill.user.service;



import com.azizONeill.user.dto.NewUserRequestDTO;
import com.azizONeill.user.dto.UpdateUserRequestDTO;
import com.azizONeill.user.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(UUID userId);

    UserDTO getUserByEmail(String userEmail);

    UserDTO createUser(NewUserRequestDTO newUserRequestDTO);

    UserDTO updateUser(UUID id, UpdateUserRequestDTO updateUserRequestDTO);

    UserDTO deleteUserById(UUID id);
}
