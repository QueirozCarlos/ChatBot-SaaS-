//package com.sistema.chatbot.security;
//
//import com.sistema.chatbot.model.Role;
//import com.sistema.chatbot.model.UserEntity;
//import com.sistema.chatbot.repository.UserRepository;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//
//    private final UserRepository userRepository; // Supondo que você tenha um repositório para buscar usuários
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Busca o usuário pelo username
//        UserEntity userEntity = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//
//        // Retorna um objeto UserDetails para autenticação no Spring Security
//        return User.builder()
//                .username(userEntity.getUsername())
//                .password(userEntity.getPassword())
//                .authorities(userEntity.getRoles().stream()
//                        .map(role -> role.getName()) //verificar depois
//                        .toArray(String[]::new))
//                .build();
//    }
//}
