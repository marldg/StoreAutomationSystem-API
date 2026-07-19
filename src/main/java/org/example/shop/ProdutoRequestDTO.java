package org.example.shop;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

// DTO de entrada: o que o cliente envia ao cadastrar/atualizar um produto
public class ProdutoRequestDTO {

    @NotBlank(message = "O nome do produto e obrigatorio")
    private String nome;

    private String descricao;

    @NotNull(message = "O preco e obrigatorio")
    @DecimalMin(value = "0.01", message = "O preco deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "A quantidade e obrigatoria")
    @Min(value = 0, message = "A quantidade nao pode ser negativa")
    private Integer quantidade;

    public ProdutoRequestDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}