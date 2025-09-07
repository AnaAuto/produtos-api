package tech.ada.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import tech.ada.dto.ProdutoDTO;

@Entity
public class Produto extends PanacheEntity  {

    public String nome;
    public String descricao;
    public Double preco;


    public Produto() {
    }


    public Produto(ProdutoDTO dto) {
        this.nome = dto.nome;
        this.descricao = dto.descricao;
        this.preco = dto.preco;
    }

    public void atualiza(tech.ada.dto.ProdutoDTO dto) {
        this.nome = dto.nome;
        this.descricao = dto.descricao;
        this.preco = dto.preco;
    }

}