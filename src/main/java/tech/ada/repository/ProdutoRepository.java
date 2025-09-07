package tech.ada.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import tech.ada.model.Produto;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    // Você pode adicionar métodos de consulta personalizados aqui no futuro.
    // Por enquanto, ele herda todos os métodos úteis do PanacheRepository.
}