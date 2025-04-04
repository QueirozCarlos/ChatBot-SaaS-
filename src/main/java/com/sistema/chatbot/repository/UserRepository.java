package com.sistema.chatbot.repository;

import com.sistema.chatbot.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String userName);

    List<UserEntity> username(String username);
}
