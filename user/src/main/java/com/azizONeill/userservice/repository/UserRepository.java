package com.azizONeill.userservice.repository;

import com.azizONeill.userservice.model.enums.Active;
import com.azizONeill.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    List<User> findAllByActive(Active active);

}
