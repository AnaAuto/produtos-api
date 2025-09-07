package tech.ada.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import tech.ada.model.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDTO {

    public Long id;

    @NotBlank(message = "O nome não pode ser vazio")
    public String nome;

    public String descricao;

    @NotNull(message = "O preço não pode ser nulo")
    @Positive(message = "O preço deve ser um valor positivo")
    public Double preco;

    // Construtor padrão
    public ProdutoDTO() {
    }

    // Construtor para converter uma Entidade em DTO
    public ProdutoDTO(Produto produto) {
        this.id = produto.id;
        this.nome = produto.nome;
        this.descricao = produto.descricao;
        this.preco = produto.preco;
    }

    // Método estático para converter uma lista de Entidades em uma lista de DTOs
    public static List<ProdutoDTO> from(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }
}