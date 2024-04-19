package com.azizONeill.userservice.service;

import com.azizONeill.userservice.client.FileStorageClient;
import com.azizONeill.userservice.model.enums.Active;
import com.azizONeill.userservice.model.enums.Role;
import com.azizONeill.userservice.model.User;
import com.azizONeill.userservice.model.UserDetails;
import com.azizONeill.userservice.repository.UserRepository;
import com.azizONeill.userservice.dto.RegisterRequest;
import com.azizONeill.userservice.dto.UserUpdateRequest;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FileStorageClient fileStorageClient;
    private final ModelMapper modelMapper;

    public User saveUser(RegisterRequest request) {
        User toSave = User.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .active(Active.ACTIVE).build();
        return userRepository.save(toSave);
    }

    public List<User> getAll() {
        return userRepository.findAllByActive(Active.ACTIVE);
    }

    public User getUserById(UUID id) {
        return findUserById(id);
    }

    public User getUserByEmail(String email) {
        return findUserByEmail(email);
    }

    public User updateUserById(UserUpdateRequest request) {
        User toUpdate = findUserById(request.getId());

        request.setUserDetails(updateUserDetails(toUpdate.getUserDetails(), request.getUserDetails()));
        modelMapper.map(request, toUpdate);

        return userRepository.save(toUpdate);
    }

    public void deleteUserById(UUID id) {
        User toDelete = findUserById(id);
        toDelete.setActive(Active.INACTIVE);
        userRepository.save(toDelete);
    }

    protected User findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    protected User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));
    }

    private UserDetails updateUserDetails(UserDetails toUpdate, UserDetails request) {
        toUpdate = toUpdate == null ? new UserDetails() : toUpdate;
        modelMapper.map(request, toUpdate);
        return toUpdate;
    }
}