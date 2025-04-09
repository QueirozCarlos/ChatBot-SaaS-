package com.sistema.chatbot.repository;

import com.sistema.chatbot.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,String> {

    UserDetails findByLogin(String login);

    List<UserEntity> login(String login);
}
