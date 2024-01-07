package com.azizONeill.user.service.serviceImpl;


import com.azizONeill.user.convert.UserConverter;
import com.azizONeill.user.dto.NewUserRequestDTO;
import com.azizONeill.user.dto.UpdateUserRequestDTO;
import com.azizONeill.user.dto.UserDTO;
import com.azizONeill.user.model.User;
import com.azizONeill.user.repository.UserRepository;
import com.azizONeill.user.service.UserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("getAllUsers started");
        List<User> users = userRepository.findAll();
        log.info("getAllUsers len: " + users.size());

        return users.stream().map(userConverter::convertUserToUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(UUID userId) {
        log.info("getUserById started");
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            return userConverter.convertUserToUserDTO(user);
        }

        return null;
    }

    @Override
    public UserDTO getUserByEmail(String userEmail) {
        log.info("getUserByEmail started");
        User user = userRepository.findByEmail(userEmail);

        if (user != null) {
            return userConverter.convertUserToUserDTO(user);
        }

        return null;
    }

    @Override
    public UserDTO createUser(NewUserRequestDTO newUserRequestDTO) {

        log.info("createUser Started");

        User newUser = new User();

        newUser.setTitle(newUserRequestDTO.getTitle());
        newUser.setFirstName(newUserRequestDTO.getFirstName());
        newUser.setLastName(newUserRequestDTO.getLastName());
        newUser.setEmail(newUserRequestDTO.getEmail());
        newUser.setPhoneNumber(newUserRequestDTO.getPhoneNumber());
        newUser.setHouseNameNumber(newUserRequestDTO.getHouseNameNumber());
        newUser.setAddressLine1(newUserRequestDTO.getAddressLine1());
        newUser.setAddressLine2(newUserRequestDTO.getAddressLine2());
        newUser.setTownCity(newUserRequestDTO.getTownCity());
        newUser.setCounty(newUserRequestDTO.getCounty());
        newUser.setPostCode(newUserRequestDTO.getPostCode());
        newUser.setPassword(newUserRequestDTO.getPassword());


        log.info("createUser before save id: " + newUser.getId());

        newUser = userRepository.save(newUser);

        log.info("createUser after save id: " + newUser.getId());

        return userConverter.convertUserToUserDTO(newUser);
    }

    @Override
    public UserDTO updateUser(UUID id, UpdateUserRequestDTO updateUserRequestDTO) {

        log.info("updateUser Started id: " + id);

        User updatedUser = userRepository.findById(id).orElse(null);

        if (updatedUser == null) {
            return null;
        }

        updatedUser.setTitle(updateUserRequestDTO.getTitle());
        updatedUser.setFirstName(updateUserRequestDTO.getFirstName());
        updatedUser.setLastName(updateUserRequestDTO.getLastName());
        updatedUser.setEmail(updateUserRequestDTO.getEmail());
        updatedUser.setPhoneNumber(updateUserRequestDTO.getPhoneNumber());
        updatedUser.setHouseNameNumber(updateUserRequestDTO.getHouseNameNumber());
        updatedUser.setAddressLine1(updateUserRequestDTO.getAddressLine1());
        updatedUser.setAddressLine2(updateUserRequestDTO.getAddressLine2());
        updatedUser.setTownCity(updateUserRequestDTO.getTownCity());
        updatedUser.setCounty(updateUserRequestDTO.getCounty());
        updatedUser.setPostCode(updateUserRequestDTO.getPostCode());

        userRepository.save(updatedUser);

        return userConverter.convertUserToUserDTO(updatedUser);

    }

    @Override
    public UserDTO deleteUserById(UUID id) {

        User deleteUser = userRepository.findById(id).orElse(null);

        if (deleteUser == null) {
            return null;
        }

        userRepository.deleteById(id);

        return userConverter.convertUserToUserDTO(deleteUser);

    }
}
