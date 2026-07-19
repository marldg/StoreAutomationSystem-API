package org.example.shop;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "tb_clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente e obrigatorio")
    private String nome;

    @NotBlank(message = "O email e obrigatorio")
    @Email(message = "Email invalido")
    @Column(unique = true)
    private String email;

    private String telefone;
}