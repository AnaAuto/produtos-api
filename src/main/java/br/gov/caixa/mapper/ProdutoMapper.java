package br.gov.caixa.mapper;

import br.gov.caixa.dto.ProdutoDTO;
import br.gov.caixa.model.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoMapper {

    public static ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.id = produto.id;
        dto.nome = produto.nome;
        dto.descricao = produto.descricao;
        dto.preco = produto.preco;
        return dto;
    }

    public static Produto toEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.nome = dto.nome;
        produto.descricao = dto.descricao;
        produto.preco = dto.preco;
        return produto;
    }

    public static List<ProdutoDTO> toDTOList(List<Produto> produtos) {
        return produtos.stream().map(ProdutoMapper::toDTO).collect(Collectors.toList());
    }
}
