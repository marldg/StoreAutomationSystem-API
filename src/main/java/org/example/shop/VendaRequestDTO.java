package org.example.shop;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// DTO de entrada: cliente envia o id do cliente e a lista de itens da venda
public class VendaRequestDTO {

    @NotNull(message = "O cliente e obrigatorio")
    private Long clienteId;

    @NotNull(message = "A lista de itens e obrigatoria")
    @Size(min = 1, message = "A venda deve ter pelo menos um item")
    private List<ItemVendaRequestDTO> itens;

    public VendaRequestDTO() {}

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public List<ItemVendaRequestDTO> getItens() { return itens; }
    public void setItens(List<ItemVendaRequestDTO> itens) { this.itens = itens; }

    // DTO interno representando cada item da venda
    public static class ItemVendaRequestDTO {

        @NotNull(message = "O produto e obrigatorio")
        private Long produtoId;

        @NotNull(message = "A quantidade e obrigatoria")
        @Min(value = 1, message = "A quantidade deve ser pelo menos 1")
        private Integer quantidade;

        public ItemVendaRequestDTO() {}

        public Long getProdutoId() { return produtoId; }
        public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }
        public Integer getQuantidade() { return quantidade; }
        public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    }
}