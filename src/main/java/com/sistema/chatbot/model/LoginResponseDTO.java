package com.sistema.chatbot.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta de login")
public record LoginResponseDTO(
    @Schema(description = "Token JWT de acesso", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    String accessToken,
    
    @Schema(description = "Token JWT para refresh", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    String refreshToken
) {}
