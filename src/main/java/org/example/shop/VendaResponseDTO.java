package org.example.shop;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VendaResponseDTO {

    private Long id;
    private String nomeCliente;
    private LocalDateTime dataVenda;
    private BigDecimal total;
    private List<ItemVendaResponseDTO> itens;

    public VendaResponseDTO(Venda venda) {
        this.id = venda.getId();
        this.nomeCliente = venda.getCliente().getNome();
        this.dataVenda = venda.getDataVenda();
        this.total = venda.getTotal();
        this.itens = venda.getItens().stream()
                .map(ItemVendaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Long getId() { return id; }
    public String getNomeCliente() { return nomeCliente; }
    public LocalDateTime getDataVenda() { return dataVenda; }
    public BigDecimal getTotal() { return total; }
    public List<ItemVendaResponseDTO> getItens() { return itens; }

    // DTO interno representando cada item na resposta
    public static class ItemVendaResponseDTO {
        private String nomeProduto;
        private Integer quantidade;
        private BigDecimal precoUnitario;
        private BigDecimal subtotal;

        public ItemVendaResponseDTO(ItemVenda item) {
            this.nomeProduto = item.getProduto().getNome();
            this.quantidade = item.getQuantidade();
            this.precoUnitario = item.getPrecoUnitario();
            this.subtotal = item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));
        }

        public String getNomeProduto() { return nomeProduto; }
        public Integer getQuantidade() { return quantidade; }
        public BigDecimal getPrecoUnitario() { return precoUnitario; }
        public BigDecimal getSubtotal() { return subtotal; }
    }
}