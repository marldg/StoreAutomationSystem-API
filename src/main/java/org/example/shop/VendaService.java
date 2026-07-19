package org.example.shop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public VendaService(VendaRepository vendaRepository,
                        ClienteRepository clienteRepository,
                        ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public VendaResponseDTO registrarVenda(VendaRequestDTO dto) {
        // Busca o cliente
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente nao encontrado com id: " + dto.getClienteId()));

        // Cria a venda
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDataVenda(LocalDateTime.now());

        List<ItemVenda> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (VendaRequestDTO.ItemVendaRequestDTO itemDTO : dto.getItens()) {
            // Busca o produto
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado com id: " + itemDTO.getProdutoId()));

            // Verifica se tem estoque suficiente
            if (produto.getQuantidade() < itemDTO.getQuantidade()) {
                throw new IllegalArgumentException(
                        "Estoque insuficiente para o produto '" + produto.getNome() +
                                "'. Disponivel: " + produto.getQuantidade() +
                                ", Solicitado: " + itemDTO.getQuantidade()
                );
            }

            // Decrementa o estoque
            produto.setQuantidade(produto.getQuantidade() - itemDTO.getQuantidade());
            produtoRepository.save(produto);

            // Cria o item da venda
            ItemVenda item = new ItemVenda();
            item.setVenda(venda);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(produto.getPreco()); // preco congelado no momento da venda

            itens.add(item);
            total = total.add(produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.getQuantidade())));
        }

        venda.setItens(itens);
        venda.setTotal(total);

        return new VendaResponseDTO(vendaRepository.save(venda));
    }

    public List<VendaResponseDTO> listarTodas() {
        return vendaRepository.findAll()
                .stream()
                .map(VendaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public VendaResponseDTO buscarPorId(Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda nao encontrada com id: " + id));
        return new VendaResponseDTO(venda);
    }
}