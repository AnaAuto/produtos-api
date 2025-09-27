package br.gov.caixa.repository;

import br.gov.caixa.model.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

}