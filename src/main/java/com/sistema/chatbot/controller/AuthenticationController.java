package com.sistema.chatbot.controller;

import com.sistema.chatbot.model.AuthenticationDTO;
import com.sistema.chatbot.model.LoginResponseDTO;
import com.sistema.chatbot.model.RegisterDTO;
import com.sistema.chatbot.model.UserEntity;
import com.sistema.chatbot.repository.UserRepository;
import com.sistema.chatbot.security.TokenService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword); //login e senha formado como token!

            var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return  ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
            if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            UserEntity newUSer = new UserEntity(data.login(), encryptedPassword, data.role());

            this.repository.save(newUSer);
            return ResponseEntity.ok().build();
    }

}
