package br.gov.caixa.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProdutoDTO {

    // O id não é obrigatório no request de criação, mas é útil no response
    public Long id;

    @NotBlank(message = "O nome é obrigatório")
    public String nome;

    public String descricao;

    @Min(value = 1, message = "O preço deve ser maior que zero")
    public double preco;
}
