//package com.sistema.chatbot.auth;
//
//import com.sistema.chatbot.service.JwtService;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.web.AuthorizeRequestsDsl;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//
//public class AuthController {
//    private final AuthenticationManager authenticationManager;
//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//
//    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtService = jwtService;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody AuthRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//        );
//        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
//        return jwtService.generateToken(userDetails);
//    }
//}
