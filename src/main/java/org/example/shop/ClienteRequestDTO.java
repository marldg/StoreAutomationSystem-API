package org.example.shop;

import jakarta.validation.constraints.*;

public class ClienteRequestDTO {

    @NotBlank(message = "O nome do cliente e obrigatorio")
    private String nome;

    @NotBlank(message = "O email e obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    private String telefone;

    public ClienteRequestDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}