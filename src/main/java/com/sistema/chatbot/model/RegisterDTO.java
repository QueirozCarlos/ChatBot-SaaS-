package com.sistema.chatbot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para registro de novo usuário")
public class RegisterDTO {
    
    @JsonProperty("nomeCompleto")
    @Schema(description = "Nome completo do usuário", example = "João da Silva")
    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 3, max = 100, message = "Nome completo deve ter entre 3 e 100 caracteres")
    private String nomeCompleto;
    
    @JsonProperty("email")
    @Schema(description = "Email do usuário", example = "usuario@email.com")
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;
    
    @JsonProperty("telefone")
    @Schema(description = "Telefone do usuário", example = "(99) 99999-9999")
    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^\\([1-9]{2}\\) [9]{0,1}[6-9]{1}[0-9]{3}-[0-9]{4}$", 
            message = "Telefone deve estar no formato (99) 99999-9999")
    private String telefone;
    
    @JsonProperty("password")
    @Schema(description = "Senha do usuário", example = "senha123")
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 20, message = "Senha deve ter entre 6 e 20 caracteres")
    private String password;
    
    @JsonProperty("role")
    @Schema(description = "Papel do usuário", example = "USER")
    @NotNull(message = "Papel do usuário é obrigatório")
    private UserRole role;

    // Getters and Setters
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
