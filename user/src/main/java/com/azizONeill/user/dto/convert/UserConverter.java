package com.azizONeill.user.dto.convert;

import com.azizONeill.user.dto.UserDTO;
import com.azizONeill.user.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private final ModelMapper modelMapper;

    public UserConverter (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertUserToUserDTO (User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertUserDTOToUser (UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
