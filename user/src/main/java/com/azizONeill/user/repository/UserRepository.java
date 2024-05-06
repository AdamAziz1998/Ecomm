package com.azizONeill.user.repository;

import com.azizONeill.user.model.User;
import com.azizONeill.user.model.enums.Active;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
    List<User> findAllByActive(Active active);

}
