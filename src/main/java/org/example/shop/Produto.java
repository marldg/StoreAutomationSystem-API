package org.example.shop;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto e obrigatorio")
    private String nome;

    private String descricao;

    @NotNull(message = "O preco e obrigatorio")
    @DecimalMin(value = "0.01", message = "O preco deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "A quantidade e obrigatoria")
    @Min(value = 0, message = "A quantidade nao pode ser negativa")
    private Integer quantidade;
}